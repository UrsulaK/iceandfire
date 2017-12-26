package iceandfire.de.service.implementation;

import java.net.URI;
import java.util.Arrays;
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

import iceandfire.de.service.config.IceAndFireConfiguration;
import iceandfire.de.service.model.House;

@Service
public class IceAndFireSearch {

	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(IceAndFireSearch.class);
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	IceAndFireConfiguration iceAndFireConfiguration;
	
	public <T> ResponseEntity<T>  searchForIceAndFireType(final String url, final Class<T> apiClass){
		try {
			UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
			UriComponents components = componentsBuilder.build(true);
			URI uri = components.toUri();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<T[]> response = (ResponseEntity<T[]>) restTemplate.exchange(uri, HttpMethod.GET, entity, apiClass);
			if(response.getStatusCode().equals(HttpStatus.OK)){
				return (ResponseEntity<T>) response;
			}else{
				LOG.info("not possible to get Objetc from url: " + url + " and object type: " + apiClass.getName() + " Status Code: " + response.getStatusCode() );
				return null;
			}
		} catch (Exception e) {
			LOG.error("Error on getting Object with url: " + url + " and object type: " + apiClass.getName(), e);
		}
		return null;
	}
	
	public <T> ResponseEntity<Object> searchHousesByName(String name, String page, String size){
		
		String url = iceAndFireConfiguration.getHousesDbUrl() + "findByNameLike?name=" + name + "&page=" + page + "&size=" + size;
		ResponseEntity<Object> responseEntity = searchForIceAndFireType(url, Object.class);
		
		return responseEntity;
	}
	public <T> ResponseEntity<Object> searchHousesByRegion(String region, String page, String size){
		
		String url = iceAndFireConfiguration.getHousesDbUrl() + "findByRegionLike?region=" + region + "&page=" + page + "&size=" + size;
		ResponseEntity<Object> responseEntity = searchForIceAndFireType(url, Object.class);
		
		return responseEntity;
	}
	public <T> ResponseEntity<Object> searchSwornMembersByName(String name, String page, String size){
		
		String url = iceAndFireConfiguration.getHousesDbUrl() + "findByNameLike?name=" + name + "&page=" + page + "&size=" + size;
		ResponseEntity<Object> responseEntity = searchForIceAndFireType(url, Object.class);
		
		return responseEntity;
	}
	
}
