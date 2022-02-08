package com.tco.models;

import com.tco.requests.game.GetUserGamesRequest;
import com.tco.requests.game.*;
import com.tco.requests.Database;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockedStatic;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameModel {
  GameModel model;

  @BeforeEach
  public void beforeEach() {
      model = new GameModel();
  }

  @Test
  @DisplayName("GameModel contructs")
  public void testGameModelConstructor() {
    assertNotNull(model);
  }


  @Test
  @DisplayName("GameModel returns all games with correct userId")
  public void testGetUserGames() {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    data.add(new HashMap<String, String>());
    data.get(0).put("Game_ID", "212");
    data.get(0).put("P1", "jane");
    data.get(0).put("P2", "john");
    data.get(0).put("Board", "{ \"a1\": \"wK\", \"h8\": \"bK\" }");
    data.get(0).put("Turn", "false");
    data.get(0).put("Started", "2021-08-16 12:34:56");
    data.get(0).put("Ended", "0000-00-00 00:00:00");
    data.add(new HashMap<String, String>());
    data.get(1).put("Game_ID", "343");
    data.get(1).put("P1", "tim");
    data.get(1).put("P2", "jane");
    data.get(1).put("Board", "{ \"a8\": \"wK\", \"h1\": \"bK\" }");
    data.get(1).put("Turn", "true");
    data.get(1).put("Started", "2021-08-02 12:12:12");
    data.get(1).put("Ended", "2021-10-04 00:00:00");

    try (MockedStatic<Database> mockDatabase = mockStatic(Database.class)) {    
        mockDatabase.when(() -> 
            Database.queryDB(anyString())
        ).thenReturn(data);

        ArrayList<Game> result = model.getUserGames("jane");
        assertEquals(data.get(0).get("Game_ID"), result.get(0).getId());
        assertEquals(data.get(1).get("Game_ID"), result.get(1).getId());

    } catch(Exception e) {
        assertEquals("Test should not throw: ", e);
    }
  }

  @Test
  @DisplayName("GameModel returns all games with correct userId")
  public void testGetGameState() {
    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    data.add(new HashMap<String, String>());
    data.get(0).put("Game_ID", "212");
    data.get(0).put("P1", "jane");
    data.get(0).put("P2", "john");
    data.get(0).put("Board", "{ \"a1\": \"wK\", \"h8\": \"bK\" }");
    data.get(0).put("Turn", "false");
    data.get(0).put("Started", "2021-08-16 12:34:56");
    data.get(0).put("Ended", "0000-00-00 00:00:00");
    data.add(new HashMap<String, String>());
    data.get(1).put("Game_ID", "343");
    data.get(1).put("P1", "tim");
    data.get(1).put("P2", "jane");
    data.get(1).put("Board", "{ \"a8\": \"wK\", \"h1\": \"bK\" }");
    data.get(1).put("Turn", "true");
    data.get(1).put("Started", "2021-08-02 12:12:12");
    data.get(1).put("Ended", "2021-10-04 00:00:00");

    try (MockedStatic<Database> mockDatabase = mockStatic(Database.class)) {    
        mockDatabase.when(() -> 
            Database.queryDB(anyString())
        ).thenReturn(data);

        ArrayList<Game> result = model.getGameState("jane", "john");
        assertEquals(data.get(0).get("Game_ID"), result.get(0).getId());
        assertEquals(data.get(1).get("Game_ID"), result.get(1).getId());

    } catch(Exception e) {
        assertEquals("Test should not throw: ", e);
    }
  }
  
}
