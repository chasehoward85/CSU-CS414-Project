package com.tco.requests.invite;

import com.tco.requests.Request;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetInviteRequest extends Request{
    private String userName; 
	private ArrayList<HashMap<String,String>> senderList;
	private final transient Logger log = LoggerFactory.getLogger(GetInviteRequest.class);
	private ArrayList<String> features;
	
	@Override
	public void prepareResponse() {
		this.userName = null;
		this.features = new ArrayList<>();
		features.add("getInvite");
		log.trace("buildResponse -> {}", this);
	}

	public String getUserName(){
		return this.userName;
	}

	public void setUnpackageResponse(ArrayList<HashMap<String, String>> senderList){
		this.senderList = senderList;
	}

	public GetInviteRequest() {
		this.requestType = "getInvite";
	}

	public GetInviteRequest(String userName) {
		this();
		this.userName = userName;
	}

	public boolean validFeature(String feature) {
		return features.contains(feature);
	}

	
    

}
