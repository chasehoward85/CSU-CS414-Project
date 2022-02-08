package com.tco.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestConfigService {
    ConfigService service;

    @BeforeEach
    public void beforeEach() {
        service = new ConfigService();
    }

    @Test
    @DisplayName("ConfigService contructs")
    public void testConfigServiceConstructor() {
        assertNotNull(service);
    }

    @Test
    @DisplayName("Should initialize Config paths")
    public void testConfigServiceInit() {
        service.initAndServe();
    }
}
