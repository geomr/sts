package com.rest.template;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractController {


	/**
	 * 
	 * @param user User
	 * @param pass Password
	 * @return {@link RestTemplate}
	 */
	public RestTemplate getRestTemplate(String user,String pass) {
    	ObjectMapper mapper = getMapper();
    	MappingJackson2HttpMessageConverter converter = getConverter(mapper);
		RestTemplate restTemplate = new RestTemplate();
    	restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(user, pass));
    	restTemplate.setMessageConverters( Collections.<HttpMessageConverter<?>> singletonList(converter) );
		return restTemplate;
	}

	private MappingJackson2HttpMessageConverter getConverter(ObjectMapper mapper) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    	converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
    	converter.setObjectMapper(mapper);
		return converter;
	}

	private ObjectMapper getMapper() {
		ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    	mapper.registerModule(new Jackson2HalModule());
		return mapper;
	}

}
