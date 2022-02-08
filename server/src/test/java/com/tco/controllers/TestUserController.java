package com.tco.controllers;

import com.tco.models.UserModel;
import com.tco.requests.Request;
import com.tco.requests.user.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestUserController {
    UserController controller;

    private UserModel mockModel = mock(UserModel.class);

    @BeforeEach
    public void beforeEach() {
        when(mockModel.createNewUser(anyString(), anyString(), anyString())).thenReturn("Someone");
        when(mockModel.createCurrentUsers(anyString(), anyString(), anyString())).thenReturn("bob");

        controller = new UserController(mockModel);
    }

    @Test
    @DisplayName("UserController contructs")
    public void testUserControllerConstructor() {
        controller = new UserController();

        assertNotNull(controller);
    }

    @Test
    @DisplayName("Handles newUser requests")
    public void testConfigRequestHandler() {
        Request testRequest = new NewUserRequest();

        try {
            JsonObject expectedObject = new JsonObject();
            JsonArray featureArray = new JsonArray();
            featureArray.add("newUser");
            expectedObject.add("features", featureArray);
            expectedObject.addProperty("requestType", "newUser");

            String response = controller.buildResponse(testRequest);

            assertEquals(expectedObject.toString(), response);
            
        } catch(Exception e) {
            assertEquals("This shouldn't be thrown: ", e);
        }
    }

    @Test
    @DisplayName("Handles currentUser requests")
    public void testCurrentUserRequestHandler() {
        Request testRequest = new CurrentUsersRequest("bob@bob.bob", "bob", "mysecretpassword");

        try {
            JsonObject expectedObject = new JsonObject();
            JsonArray featureArray = new JsonArray();
            expectedObject.addProperty("userName", "bob");
            featureArray.add("currentUser");
            expectedObject.add("features", featureArray);
            expectedObject.addProperty("requestType", "currentUser");

            String response = controller.buildResponse(testRequest);

            assertEquals(expectedObject.toString(), response);
            
        } catch(Exception e) {
            assertEquals("This shouldn't be thrown: ", e);
        }
    }
}
