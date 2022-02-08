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

public class TestGetUserGameRequest {
  private GetUserGamesRequest getUserGames;

  @BeforeEach
  public void beforeEach() {
      getUserGames = new GetUserGamesRequest(13, "John", "Jane");
      getUserGames.prepareResponse();
  }

  @Test
  @DisplayName("Request type is \"getUserGames\"")
  public void testGetUserGamesType() {
      String type = getUserGames.getRequestType();
      assertEquals("getUserGames", type);
  }

  @Test
  @DisplayName("Features includes \"getUserGames\"")
  public void testGetUserGamesFeatures(){
      assertTrue(getUserGames.validFeature("getUserGames"));
  }

  @Test
  @DisplayName("Includes ")
  public void testGetUserGamesFields() {
  }
}
