package com.tco.controllers;

import com.tco.requests.Request;
import com.tco.misc.BadRequestException;

public class ConfigController extends AbstractController {
    public ConfigController() {}

    public Request configHandler(Request config) throws BadRequestException {
        config.prepareResponse();

        return config;
    }
}
