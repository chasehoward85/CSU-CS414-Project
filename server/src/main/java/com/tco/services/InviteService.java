package com.tco.services;

import com.tco.services.AbstractService;
import com.tco.controllers.InviteController;
import com.tco.requests.invite.SendInviteRequest;
import com.tco.requests.invite.GetInviteRequest;
import com.tco.requests.Request;
import com.tco.misc.BadRequestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;

import static spark.Spark.*;

public class InviteService extends AbstractService {
	public InviteService() {
		controller = new InviteController();
	}

	@Override
	public void initAndServe() {
		path("/api", () -> {
			post("/sendInvite", (req, res) -> processHttpRequest(req, res, SendInviteRequest.class));
			post("/getInvite", (req, res) -> processHttpRequest(req, res, GetInviteRequest.class));
		});
	}
}
