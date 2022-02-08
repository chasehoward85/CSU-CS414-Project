package com.tco.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import com.tco.models.InviteModel;
import com.tco.requests.Request;
import com.tco.requests.invite.SendInviteRequest;
import com.tco.requests.invite.GetInviteRequest;
import com.tco.misc.BadRequestException;

public class InviteController extends AbstractController {
	private InviteModel model;

	public InviteController() {
		model = new InviteModel();
	}

	public InviteController(InviteModel testModel) {
		model = testModel;
	}

	public Request sendInviteHandler(Request sendInviteRequest) throws BadRequestException {
		SendInviteRequest inviteRequest = (SendInviteRequest)sendInviteRequest;

		Boolean sent = model.createInvite(inviteRequest);
		
		inviteRequest.setSent(sent);
		inviteRequest.prepareResponse();

		return inviteRequest;
	}

	public Request getInviteHandler(Request getInviteRequest) throws BadRequestException{
		GetInviteRequest inviteRequest = (GetInviteRequest) getInviteRequest;
		inviteRequest.setUnpackageResponse(model.getInvite(inviteRequest.getUserName()));
		inviteRequest.prepareResponse();
		return inviteRequest;
	}

	
}
