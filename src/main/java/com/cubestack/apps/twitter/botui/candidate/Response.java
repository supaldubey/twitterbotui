/**
 * 
 */
package com.cubestack.apps.twitter.botui.candidate;

import java.util.List;

/**
 * @author Supal Dubey
 *
 */
public class Response {

	private boolean success = true;
	private String message;
	private TwitterUserCandidate twitterUserCandidate;
	private List<TweetCandidate> tweetCandidates;
	private int totalPages;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TwitterUserCandidate getTwitterUserCandidate() {
		return twitterUserCandidate;
	}
	public void setTwitterUserCandidate(TwitterUserCandidate twitterUserCandidate) {
		this.twitterUserCandidate = twitterUserCandidate;
	}
	public List<TweetCandidate> getTweetCandidates() {
		return tweetCandidates;
	}
	public void setTweetCandidates(List<TweetCandidate> tweetCandidates) {
		this.tweetCandidates = tweetCandidates;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	
}
