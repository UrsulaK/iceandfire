package iceandfire.de.service.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "iceandfire")
@EnableScheduling
public class IceAndFireConfig {
	
	@Value("${iceandfire.houses.baseUrl}")
	private String housesBaseUrl;
	
	@Value("${iceandfire.characters.baseUrl}")
	private String charactersBaseUrl;
	
	private HashMap<String, Integer> regions;
	
	@Primary
	@Bean("restTemplate")
	public RestTemplate getRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(getMarshallingHttpMessageConverter());
		restTemplate.setMessageConverters(converters);
		((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(15000);
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(15000);
		return restTemplate;
	}
	@Bean("marshallingHttpMessageConverter")
	public MarshallingHttpMessageConverter getMarshallingHttpMessageConverter() {
		MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
		converter.setMarshaller(getJaxb2Marshaller());
		converter.setUnmarshaller(getJaxb2Marshaller());
		return converter;
	}
	@Bean("jaxb2Marshaller")
	public Jaxb2Marshaller getJaxb2Marshaller(){
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setPackagesToScan("iceandfire.de.service.api");
		
		return jaxb2Marshaller;
	}
	public String getHousesBaseUrl() {
		return housesBaseUrl;
	}
	public void setHousesBaseUrl(String housesBaseUrl) {
		this.housesBaseUrl = housesBaseUrl;
	}
	public String getCharactersBaseUrl() {
		return charactersBaseUrl;
	}
	public void setCharactersBaseUrl(String charactersBaseUrl) {
		this.charactersBaseUrl = charactersBaseUrl;
	}
	public HashMap<String, Integer> getRegions() {
		return regions;
	}
	public void setRegions(HashMap<String, Integer> regions) {
		this.regions = regions;
	}
	
	
}
