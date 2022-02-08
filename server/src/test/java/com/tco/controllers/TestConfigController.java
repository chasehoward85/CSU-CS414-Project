package com.tco.controllers;

import com.tco.requests.Request;
import com.tco.requests.config.ConfigRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestConfigController {
    ConfigController controller;

    @BeforeEach
    public void beforeEach() {
        controller = new ConfigController();
    }

    @Test
    @DisplayName("ConfigController contructs")
    public void testConfigControllerConstructor() {
        assertNotNull(controller);
    }

    @Test
    @DisplayName("Handles config requests")
    public void testConfigRequestHandler() {
        Request testRequest = new ConfigRequest();

        try {
            String response = controller.buildResponse(testRequest);
            JsonObject expectedObject = new JsonObject();
            expectedObject.addProperty("serverName", "t07 The Kingsman");
            JsonArray featureArray = new JsonArray();
            featureArray.add("config");
            expectedObject.add("features", featureArray);
            expectedObject.addProperty("requestType", "config");

            assertEquals(expectedObject.toString(), response);

        } catch(Exception e) {
            assertEquals("This shouldn't be thrown", e);
        }
    }
}
