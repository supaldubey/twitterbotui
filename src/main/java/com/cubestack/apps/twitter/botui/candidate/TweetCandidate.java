/**
 * 
 */
package com.cubestack.apps.twitter.botui.candidate;

/**
 * @author Supal Dubey
 *
 */
public class TweetCandidate {

	private long id;
	private String tweetId;
	private String statusText;
	private String tweetBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTweetId() {
		return tweetId;
	}

	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getTweetBy() {
		return tweetBy;
	}

	public void setTweetBy(String tweetBy) {
		this.tweetBy = tweetBy;
	}
}
