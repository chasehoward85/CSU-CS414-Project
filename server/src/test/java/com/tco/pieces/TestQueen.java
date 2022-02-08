package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestQueen {
  AbstractPiece queen;

  @BeforeEach
  public void beforeEach() {
    queen = new Queen();
  }

  @Test
  @DisplayName("Queen instatiates")
  public void testQueenInstatiates() {
    assertNotNull(queen);
  }

  @Test
  @DisplayName("Queen validates moves")
  public void testRookCanMove() {
    Board board = new Board();
    assertEquals("wB", board.get("c1"));
    Move move = new Move("d1", "d6", "wQ");

    assertFalse(queen.checkMove(board, move));

    board.remove("d2");

    assertTrue(queen.checkMove(board, move));
  }
}
