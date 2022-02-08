package com.tco.requests.game;

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

public class TestGetGameStateRequest {
  private GetGameStateRequest getGameState;

  @BeforeEach
  public void beforeEach() {
      getGameState = new GetGameStateRequest("John", "Jane");
      getGameState.prepareResponse();
  }

  @Test
  @DisplayName("Request type is \"getGameState\"")
  public void testGetGameStateType() {
      String type = getGameState.getRequestType();
      assertEquals("getGameState", type);
  }

  @Test
  @DisplayName("Features includes \"getGameState\"")
  public void testGetGameStateFeatures(){
      assertTrue(getGameState.validFeature("getGameState"));
  }

  @Test
  @DisplayName("Includes ")
  public void testGetGameStateFields() {
  }
}
