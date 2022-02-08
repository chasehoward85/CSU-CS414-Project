package com.tco.requests.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestNewUserRequest {
    private NewUserRequest newUser;

    @BeforeEach
    public void beforeEach() {
        newUser = new NewUserRequest("bob@test.example", "mySecretPassword", "bob");
        newUser.prepareResponse();
    }

    @Test
    @DisplayName("Request type is \"newUser\"")
    public void testNewUserType() {
        String type = newUser.getRequestType();
        assertEquals("newUser", type);
    }

    @Test
    @DisplayName("Features includes \"newUser\"")
    public void testNewUserFeatures(){
        assertTrue(newUser.validFeature("newUser"));
    }

    @Test
    @DisplayName("Includes new user name and not email or password in response on sucess")
    public void testNewUserFields() {
        assertNull(newUser.getEmail());
        assertNull(newUser.getPassword());
        assertEquals("bob", newUser.getUserName());
    }
}