package com.tco.controllers;

import com.tco.models.GameModel;
import com.tco.requests.Request;
import com.tco.requests.game.*;
import com.tco.misc.GameValidator;

import java.time.LocalDate;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameController {
  GameController controller;

  private GameModel mockModel = mock(GameModel.class);
  private GameValidator mockValidator = mock(GameValidator.class);
  ArrayList<Game> gameArray = new ArrayList<Game>();

  @BeforeEach
  public void beforeEach() {
    gameArray.add(new Game(
      131,
      "Jane",
      "John",
      new Board(new HashMap<String, String>(TestBoardStates.INITIAL_BOARD)),
      false,
      LocalDate.parse("2021-09-21"),
      LocalDate.parse("2021-09-25")
    ));

    when(mockModel.getUserGames(anyString())).thenReturn(gameArray);
    when(mockModel.getGameState(anyString(), anyString())).thenReturn(gameArray);

    controller = new GameController(mockModel, mockValidator);
  }

  @Test
  @DisplayName("UserController contructs")
  public void testUserControllerConstructor() {
    controller = new GameController();

    assertNotNull(controller);
  }

  @Test
  @DisplayName("Handles requests to get all user games")
  public void testGetUserGames() {
    Request testRequest = new GetUserGamesRequest("Jane");

    try {
      JsonObject expectedObject = new JsonObject();
      String gamesString = new Gson().toJson(gameArray);
      expectedObject.addProperty("games", gamesString);
      JsonArray featureArray = new JsonArray();
      featureArray.add("getUserGames");
      expectedObject.add("features", featureArray);
      expectedObject.addProperty("requestType", "getUserGames");

      String response = controller.buildResponse(testRequest);
      assertTrue(response.contains("131"));
      assertTrue(response.contains("Jane"));
      assertTrue(response.contains("John"));
      
    } catch(Exception e) {
       assertEquals("This shouldn't be thrown: ", e);
   }
 }
 
 @Test
 @DisplayName("Handles requests to get game states between two users.")
 public void testGetGameState() {
   Request testRequest = new GetGameStateRequest("Jane", "John");
 
   try {
     JsonObject expectedObject = new JsonObject();
     String gamesString = new Gson().toJson(gameArray);
     expectedObject.addProperty("gameState", gamesString);
     JsonArray featureArray = new JsonArray();
     featureArray.add("getGameState");
     expectedObject.add("features", featureArray);
     expectedObject.addProperty("requestType", "getGameState");
 
     String response = controller.buildResponse(testRequest);

      assertTrue(response.contains("131"));
      assertTrue(response.contains("Jane"));
      assertTrue(response.contains("John"));
        
    } catch(Exception e) {
        assertEquals("This shouldn't be thrown: ", e);
    }
  }

  // @Test
  // @DisplayName("Handles requests to make moves")
  // public void testMakeMove() {
  //   when(mockValidator.validateAndMove(any(), any(), any())).thenReturn(true);

  //   Move testMove = new Move("b8", "c6", "bN");
  //   Request testRequest = new MakeMoveRequest(gameArray.get(0).getBoard(), "John", 131, testMove);

  //   try {
  //     JsonObject expectedObject = new JsonObject();
  //     String gamesString = new Gson().toJson(gameArray);
  //     expectedObject.addProperty("board", gamesString);
  //     JsonArray featureArray = new JsonArray();
  //     featureArray.add("makeMove");
  //     expectedObject.add("features", featureArray);
  //     expectedObject.addProperty("requestType", "makeMove");

  //     String response = controller.buildResponse(testRequest);

  //     assertTrue(response.contains("\"valid\":true"));
        
  //   } catch(Exception e) {
  //       assertEquals("This shouldn't be thrown: ", e);
  //   }
  // }
}
