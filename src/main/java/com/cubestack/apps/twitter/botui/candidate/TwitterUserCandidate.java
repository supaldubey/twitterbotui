package com.cubestack.apps.twitter.botui.candidate;
/**
 * 
 */

import java.util.LinkedList;
import java.util.List;

import twitter4j.Status;

/**
 * @author supal
 *
 */
public class TwitterUserCandidate {

	private long id;
	private long twitterId;
	private String screenName;
	private List<String> tweetLists = new LinkedList<>();
	private Status status;
	private int pin;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(long twitterId) {
		this.twitterId = twitterId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<String> getTweetLists() {
		return tweetLists;
	}

	public void setTweetLists(List<String> tweetLists) {
		this.tweetLists = tweetLists;
	}

}
