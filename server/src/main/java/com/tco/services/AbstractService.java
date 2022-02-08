package com.tco.services;

import com.tco.controllers.AbstractController;
import com.tco.misc.BadRequestException;
import com.tco.misc.JSONValidator;
import com.tco.requests.Request;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

import static spark.Spark.*;

public abstract class AbstractService {
    protected final Logger log = LoggerFactory.getLogger(AbstractService.class);

    private final int HTTP_OK = 200;
    private final int HTTP_BAD_REQUEST = 400;
    private final int HTTP_SERVER_ERROR = 500;

    protected AbstractController controller;

    abstract public void initAndServe();

    /* You shouldn't need to change what is found below. */

    protected String processHttpRequest(spark.Request httpRequest, spark.Response httpResponse, Type requestType) {
        setupResponse(httpResponse);
        String jsonString = httpRequest.body();
        try {
            JSONValidator.validate(jsonString, requestType);
            Request requestObj = new Gson().fromJson(jsonString, requestType);
            return buildJSONResponse(requestObj);
        } catch (IOException | BadRequestException e) {
            log.info("Bad Request - {}", e.getMessage());
            httpResponse.status(HTTP_BAD_REQUEST);
        } catch (Exception e) {
            log.info("Server Error - ", e);
            httpResponse.status(HTTP_SERVER_ERROR);
        }
        return jsonString;
    }

    protected void setupResponse(spark.Response response) {
        response.type("application/json");
        response.header("Access-Control-Allow-Origin", "*");
        response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        response.status(HTTP_OK);
    }

    protected String buildJSONResponse(Request request) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, BadRequestException {
        String responseBody = controller.buildResponse(request);
        log.info("Response - {}", responseBody);
        return responseBody;
    }
}