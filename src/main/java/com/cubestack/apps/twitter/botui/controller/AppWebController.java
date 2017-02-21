/**
 * 
 */
package com.cubestack.apps.twitter.botui.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cubestack.apps.twitter.botui.candidate.Response;
import com.cubestack.apps.twitter.botui.rest.TwitterRestCoordinator;

/**
 * @author Supal Dubey
 *
 */
@Controller
public class AppWebController {
	
	
	@Autowired
	private TwitterRestCoordinator restCoordinator;
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest httpServletRequest, Map<String, Object> model) {
		
		if(httpServletRequest.getAttribute("message") != null) {
			model.put("message", httpServletRequest.getAttribute("message"));
		}
		return "index";
	}

	
	@RequestMapping("/{screenName}")
	public String profile(HttpServletRequest httpServletRequest, @PathVariable("screenName") String screenName, Map<String, Object> model) {
		String responsePage = "index";
		//Validate user first
		Response userResponse = restCoordinator.findUser(screenName);
		if(userResponse.isSuccess()) {
			model.put("user", userResponse.getTwitterUserCandidate());
			responsePage = "profile";
		} 
		//If we have list name, fetch
		if(httpServletRequest.getParameter("listName") != null && httpServletRequest.getParameter("listName").length() > 2) {
			// Fetch tweets for this list
			String page = httpServletRequest.getParameter("page") == null ? "" : "0";
			Response listResponse = restCoordinator.findTweets(screenName, httpServletRequest.getParameter("listName"), page);
			if(listResponse.isSuccess()) {
				model.put("screenName", screenName);
				model.put("listName", httpServletRequest.getParameter("listName"));
				model.put("tweets", listResponse.getTweetCandidates());
				model.put("page", page);
				responsePage = "tweets";
			}
		} else {
			//Failed
			httpServletRequest.setAttribute("message", userResponse.getMessage());
		}
		return responsePage;
	}
	
}
 