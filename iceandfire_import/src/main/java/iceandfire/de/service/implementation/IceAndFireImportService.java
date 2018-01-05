package iceandfire.de.service.implementation;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.net.UrlEscapers;

import iceandfire.de.service.api.ApiCharacter;
import iceandfire.de.service.api.ApiHouse;
import iceandfire.de.service.configuration.IceAndFireConfig;
import iceandfire.de.service.converter.IceAndFireConverter;
import iceandfire.de.service.model.House;
import iceandfire.de.service.model.SwornMember;

@Service
public class IceAndFireImportService {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(IceAndFireImportService.class);
	private Map<String, Class> failedDownloads = new HashMap<>();

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("apiRestTemplate")
	private RestTemplate apiRestTemplate;
	
	@Autowired
	private IceAndFireConverter converter;

	@Autowired
	private IceAndFireConfig iceAndFireConfig;
	
	private boolean dataIsImported = false;

	@SuppressWarnings("unchecked")
	public <T> T getIceAndFireTypeFromApi(final String url, final Class<T> apiClass) {
		try {
			UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
			UriComponents components = componentsBuilder.build(true);
			URI uri = components.toUri();

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<T[]> response = (ResponseEntity<T[]>) apiRestTemplate.exchange(uri, HttpMethod.GET, entity, apiClass);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return (T) response.getBody();
			} else {
				LOG.info("not possible to get Objetc from url: " + url + " and object type: " + apiClass.getName()
						+ " Status Code: " + response.getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("Error on getting Object with url: " + url + " and object type: " + apiClass.getName(), e);
		}
		return null;
	}

	public <T> ResponseEntity<T> postIceAndFireType(final String url, Object request, final Class<T> apiClass) {
		try {
			UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
			UriComponents components = componentsBuilder.build(true);
			URI uri = components.toUri();
			ResponseEntity<T> response = restTemplate.postForEntity(uri, request, apiClass);
			if (response.getStatusCode().equals(HttpStatus.CREATED)) {
				return response;
			} else {
				LOG.info("not possible to get Objetc from url: " + url + " and object type: " + apiClass.getName()
						+ " Status Code: " + response.getStatusCode());
				return null;
			}

		} catch (Exception e) {
			LOG.error("Error on post request for Object with url: " + url + " and object type: " + apiClass.getName(),
					e);
		}

		return null;
	}


	public iceandfire.de.service.api.ApiCharacter getCharacter(String url) {
		iceandfire.de.service.api.ApiCharacter character = getIceAndFireTypeFromApi(url,
				iceandfire.de.service.api.ApiCharacter.class);
		return character;
	}

	public List<ApiHouse> searchHousesByRegion(String region, String page, String pageSize) {
		String encodedRegion = "";

		encodedRegion = UrlEscapers.urlFragmentEscaper().escape(region);

		String url = iceAndFireConfig.getHousesBaseUrl() + "?region=" + encodedRegion + "&page=" + page + "&pageSize="
				+ pageSize;

		ApiHouse[] houses = getIceAndFireTypeFromApi(url, ApiHouse[].class);
		if (houses != null) {
			return Arrays.asList(houses);
		}
		return null;
	}

	public List<House> getHousesByRegion(String region, String page, String pageSize) {
		List<House> houses = new ArrayList<>();
		List<ApiHouse> apiHouses = searchHousesByRegion(region, page, pageSize);
		if (apiHouses != null) {
			for (ApiHouse apiHouse : apiHouses) {
				House house = converter.convertApiHouseToHouse(apiHouse);
				for (String url : apiHouse.getSwornMembers()) {
					ApiCharacter character = getCharacter(url);
					if (character != null) {
						SwornMember swornMember = converter.convertApiCharacterToSwornMember(character);

						ResponseEntity<SwornMember> responseEntity = postIceAndFireType(
								iceAndFireConfig.getCharactersDbUrl(), swornMember, SwornMember.class);
						if (responseEntity != null) {
							// String location =
							// responseEntity.getHeaders().getLocation().toString();
							house.getSwornMembers().put(swornMember.getId(), swornMember.getName());
						}
					}
				}
				postIceAndFireType(iceAndFireConfig.getHousesDbUrl(), house, House.class);
				houses.add(house);

			}
		}
		return houses;
	}

	public Map<String, String> importFireAndIceData() {
		Map<String, String> response = new HashMap<>();
		if(dataIsImported){
			response.put("message", "Daten sind bereits importiert.");
		} else {
			Map<String, Integer> regions = iceAndFireConfig.getRegions();

			for (String region : regions.keySet()) {
				for (int i = 1; i <= regions.get(region); i++) {
					String page = String.valueOf(i);
					getHousesByRegion(region, page, "10");
				}
			}
			response.put("message", "Daten wurden erfolgreich importiert.");
			dataIsImported = true;
		}
		return response;
	}	

}
