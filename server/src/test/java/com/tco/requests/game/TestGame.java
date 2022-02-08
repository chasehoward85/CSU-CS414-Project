package com.tco.requests.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestGame {
  private Game game;

  @BeforeEach
  public void beforeEach() {
    game = new Game(13, "John", "Jane");
  }

  @Test
  @DisplayName("Should initalize a new game")
  public void testGameInstatiation() {
    assertEquals("13", game.getId());
    assertEquals("John", game.getP1());
    assertEquals("Jane", game.getP2());
    assertTrue(game.getTurn());
    assertEquals(LocalDate.now(), game.getStartDate());
    assertNull(game.getEndDate());

    Board board = game.getBoard();

    assertTrue(TestBoardStates.INITIAL_BOARD.keySet().containsAll(board.keySet()));
    assertTrue(TestBoardStates.INITIAL_BOARD.equals(board.getRawBoard()));
  }
}
