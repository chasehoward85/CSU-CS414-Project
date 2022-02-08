package com.tco.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestGameService {
  GameService service;

  @BeforeEach
  public void beforeEach() {
      service = new GameService();
  }

  @Test
  @DisplayName("GameService contructs")
  public void testGameServiceConstructor() {
      assertNotNull(service);
  }

  @Test
  @DisplayName("Should initialize Game paths")
  public void testGameServiceInit() {
      service.initAndServe();
  }
}
