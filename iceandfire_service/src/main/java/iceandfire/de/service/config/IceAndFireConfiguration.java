package iceandfire.de.service.config;

import java.util.ArrayList;
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
@EnableScheduling
public class IceAndFireConfiguration {

	@Value("${iceandfire.houses.dbUrl}")
	private String housesDbUrl;
	
	@Value("${iceandfire.swornMembers.dbUrl}")
	private String swornMembersDbUrl;
	
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
	public String getHousesDbUrl() {
		return housesDbUrl;
	}
	public void setHousesDbUrl(String housesDbUrl) {
		this.housesDbUrl = housesDbUrl;
	}
	public String getSwornMembersDbUrl() {
		return swornMembersDbUrl;
	}
	public void setSwornMembersDbUrl(String swornMembersDbUrl) {
		this.swornMembersDbUrl = swornMembersDbUrl;
	}
	
	
}
