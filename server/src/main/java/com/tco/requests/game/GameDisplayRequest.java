package com.tco.requests.game;

import com.tco.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameDisplayRequest extends Request {
  private final transient Logger log = LoggerFactory.getLogger(GameDisplayRequest.class);
  private ArrayList<String> features;
  private String userName;
  private ArrayList<HashMap<String, String>> setDisplay;


  @Override
  public void prepareResponse() {
    this.features = new ArrayList<>();
    features.add("gameDisplay");
    log.trace("buildResponse -> {}", this);
  }

  	public GameDisplayRequest() {
		this.requestType = "gameDisplay";
	}

	public GameDisplayRequest(String username) {
		this();
		this.userName = username;
    }

	public boolean validFeature(String feature) {
		return features.contains(feature);
	}

	public void setGameDisplay(ArrayList<HashMap<String, String>> setDisplay){
		this.setDisplay = setDisplay;
	}
 
	public String getUsername(){
		return this.userName;
	}
}
