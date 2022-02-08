package com.tco.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUserService {
    UserService service;

    @BeforeEach
    public void beforeEach() {
        service = new UserService();
    }

    @Test
    @DisplayName("UserService contructs")
    public void testUserServiceConstructor() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Should initialize User paths")
    public void testUserServiceInit() {
        service.initAndServe();
    }
}