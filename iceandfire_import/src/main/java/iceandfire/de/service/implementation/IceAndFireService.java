package iceandfire.de.service.implementation;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.net.UrlEscapers;

import iceandfire.de.service.api.House;

@Service
public class IceAndFireService {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(IceAndFireService.class);
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Value("${iceandfire.houses.baseUrl}")
	private String housesBaseUrl;
	
	@Value("${iceandfire.characters.baseUrl}")
	private String charactersBaseUrl;
	
	@SuppressWarnings("unchecked")
	public <T> T getIceAndFireType(final String url, final Class<T> apiClass){
		try {
			UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
			UriComponents components = componentsBuilder.build(true);
			URI uri = components.toUri();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<T[]> response = (ResponseEntity<T[]>) restTemplate.exchange(uri, HttpMethod.GET, entity, apiClass);
			return (T) response.getBody();
		} catch (Exception e) {
			LOG.error("Error on getting Object with url: " + url + " and object type: " + apiClass.getName(), e);
		}
		return null;
	}
	
	public House getHouseById(String id){
		
		String url = housesBaseUrl + id;
		House house = getIceAndFireType(url, House.class);
		
		return house;
	}
	
	public iceandfire.de.service.api.Character getCharacterById(String id){
		String url = charactersBaseUrl + id;
		iceandfire.de.service.api.Character character = getIceAndFireType(url, iceandfire.de.service.api.Character.class);
		return character;
	}
	
	public List<House> searchHousesByRegion(String region, String page, String pageSize){
		String encodedRegion = "";
		
		encodedRegion = UrlEscapers.urlFragmentEscaper().escape(region);

		String url = housesBaseUrl + "?region=" + encodedRegion + "&page=" + page + "&pageSize=" + pageSize;
		
		House[] houses = getIceAndFireType(url, House[].class);
		if(houses != null){
			return Arrays.asList(houses);
		}
		return null;
	}
}
