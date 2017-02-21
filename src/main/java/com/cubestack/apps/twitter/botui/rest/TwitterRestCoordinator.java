/**
 * 
 */
package com.cubestack.apps.twitter.botui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cubestack.apps.twitter.botui.candidate.Response;

/**
 * @author Supal Dubey
 *
 */

@Service
public class TwitterRestCoordinator {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${app.rest.url}")
	private String baseUrl;

	public Response findUser(String screenName) {
		ResponseEntity<Response> response = restTemplate.getForEntity(baseUrl + screenName, Response.class);
		return response.getBody();
	}


	public Response findTweets(String screenName, String listName, String page) {
		ResponseEntity<Response> response = restTemplate
				.getForEntity(baseUrl + screenName + "/" + listName + "/" + page, Response.class);
		return response.getBody();
	}
}
