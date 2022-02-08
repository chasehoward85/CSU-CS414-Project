package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestBishop {
  AbstractPiece bishop;

  @BeforeEach
  public void beforeEach() {
    bishop = new Bishop();
  }

  @Test
  @DisplayName("Bishop instatiates")
  public void testBishopInstatiates() {
    assertNotNull(bishop);
  }

  @Test
  @DisplayName("Bishop validates moves")
  public void testBishopCanMove() {
    Board board = new Board();
    assertEquals("wB", board.get("c1"));
    Move move = new Move("c1", "h6", "wB");

    assertFalse(bishop.checkMove(board, move));

    board.remove("d2");


    assertTrue(bishop.checkMove(board, move));
  }
}
