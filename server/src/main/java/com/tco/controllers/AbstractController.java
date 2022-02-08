package com.tco.controllers;

import com.tco.models.AbstractModel;
import com.tco.requests.Request;
import com.tco.misc.BadRequestException;

import com.google.gson.Gson;
import java.lang.IllegalAccessException;
import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;

public abstract class AbstractController {
    AbstractModel model;

    public String buildResponse(Request request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, BadRequestException {
        Object response = getClass().getDeclaredMethod(request.getRequestType() + "Handler", Request.class).invoke(this, request);

        return new Gson().toJson(response);
    };
}
