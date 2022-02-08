package com.tco.requests.invite;

import com.tco.requests.Request;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendInviteRequest extends Request {
	private String sender;
	private String receiver;
	private Boolean sent;
	private final transient Logger log = LoggerFactory.getLogger(SendInviteRequest.class);
	private ArrayList<String> features;
	
	@Override
	public void prepareResponse() {
		this.sender = null;
		this.receiver = null;
		this.features = new ArrayList<>();

		features.add("sendInvite");
		log.trace("buildResponse -> {}", this);
	}

	public SendInviteRequest() {
		this.requestType = "sendInvite";
	}

	public SendInviteRequest(String sender, String receiver) {
		this();
		this.sender = sender;
		this.receiver = receiver;
	}

	public boolean validFeature(String feature) {
		return features.contains(feature);
	}

	public String getSender() {
		return this.sender;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setSent(Boolean sent) {
		this.sent = sent;
	}
}
