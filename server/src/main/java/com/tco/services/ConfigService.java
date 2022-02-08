package com.tco.services;

import com.tco.controllers.ConfigController;
import com.tco.requests.Request;
import com.tco.services.AbstractService;
import com.tco.requests.config.ConfigRequest;
import com.tco.misc.BadRequestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;

import static spark.Spark.*;

public class ConfigService extends AbstractService {
    public ConfigService() {
        controller = new ConfigController();
    }

    @Override
    public void initAndServe() {
        path("/api", () -> {
            post("/config", (req, res) -> processHttpRequest(req, res, ConfigRequest.class));
        });
    }
}