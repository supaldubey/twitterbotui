/**
 * 
 */
package com.cubestack.apps.twitter.botui;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * @author Supal Dubey
 *
 */
@Configuration
public class AppConfiguration {

	
	@Value("${app.rest.password}")
	private String authPassword;
	
	@Value("${app.rest.url}")
	private String baseUrl;

	@Value("${app.rest.userName}")
	private String authName;

	@Bean
	public RestTemplate getTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = Collections
				.<ClientHttpRequestInterceptor>singletonList(new BasicAuthorizationInterceptor(authName, authPassword));
		restTemplate.setRequestFactory(
				new InterceptingClientHttpRequestFactory(restTemplate.getRequestFactory(), interceptors));
		
		return restTemplate;
	}
}
