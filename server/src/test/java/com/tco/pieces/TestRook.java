package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestRook {
  AbstractPiece rook;

  @BeforeEach
  public void beforeEach() {
    rook = new Rook();
  }

  @Test
  @DisplayName("Rook instatiates")
  public void testRookInstatiates() {
    assertNotNull(rook);
  }

  @Test
  @DisplayName("Rook validates moves")
  public void testRookCanMove() {
    Board board = new Board();
    assertEquals("wB", board.get("c1"));
    Move move = new Move("a8", "a6", "bR");

    assertFalse(rook.checkMove(board, move));

    board.remove("a7");

    assertTrue(rook.checkMove(board, move));
  }
}
