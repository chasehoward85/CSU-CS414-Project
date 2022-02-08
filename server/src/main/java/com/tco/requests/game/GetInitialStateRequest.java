package com.tco.requests.game;

import com.tco.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetInitialStateRequest extends Request {
  private final transient Logger log = LoggerFactory.getLogger(GetUserGamesRequest.class);
  private ArrayList<String> features;
  private Integer id;
  private String p1;
  private String p2;
  private Boolean accepted;
  private Boolean created;

  @Override
  public void prepareResponse() {
	this.id = null;
	this.p1 = null;
	this.p2 = null;
	this.accepted = null;
	this.created = true;
    this.features = new ArrayList<>();
    features.add("getInitialState");
    log.trace("buildResponse -> {}", this);
  }

  	public GetInitialStateRequest() {
		this.requestType = "getInitialState";
	}

	public GetInitialStateRequest(int id, String p1, String p2, boolean accepted) {
		this();
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
		this.accepted = accepted;
	}

	public boolean validFeature(String feature) {
		return features.contains(feature);
	}

	public Integer getID() {
		return this.id;
	}

	public String getP1() {
		return this.p1;
	}

	public String getP2() {
		return this.p2;
	}

	public boolean getAccepted() {
		return this.accepted;
	}
}
