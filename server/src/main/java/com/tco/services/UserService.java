package com.tco.services;

import com.tco.services.AbstractService;
import com.tco.controllers.UserController;
import com.tco.requests.user.NewUserRequest;
import com.tco.requests.user.CurrentUsersRequest;
import com.tco.requests.Request;
import com.tco.misc.BadRequestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;
import com.tco.requests.user.GetUserStatsRequest;
import static spark.Spark.*;

public class UserService extends AbstractService {
    public UserService() {
        controller = new UserController();
    }

    @Override
    public void initAndServe() {
        path("/api", () -> {
            post("/newUser", (req, res) -> processHttpRequest(req, res, NewUserRequest.class));
            post("/currentUser", (req, res) -> processHttpRequest(req, res, CurrentUsersRequest.class));
            post("/getStats", (req, res) -> processHttpRequest(req, res, GetUserStatsRequest.class));

        });
    }
}