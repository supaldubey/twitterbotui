/**
 * 
 */
package com.cubestack.apps.twitter.botui.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	private static final Logger LOG = Logger.getLogger(AppWebController.class);
	
	
	private static final String PROFILE_PAGE_REF = "profile";
	private static final String TWEETS_PAGE_REF = "tweets";
	private static final String HOME_PAGE_REF = "index";
	@Autowired
	private TwitterRestCoordinator restCoordinator;
	
	@RequestMapping("/")
	public String welcome(HttpServletRequest httpServletRequest, Map<String, Object> model) {
		
		if(httpServletRequest.getAttribute("message") != null) {
			model.put("message", httpServletRequest.getAttribute("message"));
		}
		return HOME_PAGE_REF;
	}

	
	@RequestMapping("/{screenName}")
	public String profile(HttpServletRequest httpServletRequest, @PathVariable("screenName") String twitterScreenName, Map<String, Object> model) {
		String responsePage = HOME_PAGE_REF;
		//Validate user first
		Response userResponse = restCoordinator.findUser(twitterScreenName);
		if(userResponse.isSuccess()) {
			model.put("user", userResponse.getTwitterUserCandidate());
			responsePage = PROFILE_PAGE_REF;
		} 
		//If we have list name, fetch
		if(httpServletRequest.getParameter("listName") != null && httpServletRequest.getParameter("listName").length() > 2) {
			// Fetch tweets for this list
			responsePage = processForTweets(twitterScreenName, model, httpServletRequest);
		} else {
			//Failed
			httpServletRequest.setAttribute("message", userResponse.getMessage());
		}
		return responsePage;
	}


	private String processForTweets(String twitterScreenName, Map<String, Object> model, HttpServletRequest httpServletRequest) {
		String page = httpServletRequest.getParameter("page") == null ? "" : "0";
		Response listResponse = restCoordinator.findTweets(twitterScreenName, httpServletRequest.getParameter("listName"), page);
		if(listResponse.isSuccess()) {
			model.put("screenName", twitterScreenName);
			model.put("listName", httpServletRequest.getParameter("listName"));
			model.put(TWEETS_PAGE_REF, listResponse.getTweetCandidates());
			model.put("page", page);
			model.put("totalPages", listResponse.getTotalPages());
			return TWEETS_PAGE_REF;
		} else {
			//Failed
			httpServletRequest.setAttribute("message", listResponse.getMessage());
		}
		return HOME_PAGE_REF;
	}
	
	@ExceptionHandler
	public String handle(Exception exception) {
		LOG.error("Exception in flow", exception);
		//Fallback to Home Page?
		return HOME_PAGE_REF;
	}
}
 