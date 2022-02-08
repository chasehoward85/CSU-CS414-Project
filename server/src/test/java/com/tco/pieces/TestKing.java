package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestKing {
  AbstractPiece king;

  @BeforeEach
  public void beforeEach() {
    king = new King();
  }

  @Test
  @DisplayName("King instatiates")
  public void testKingInstatiates() {
    assertNotNull(king);
  }

  @Test
  @DisplayName("King validates moves")
  public void testKingCanMove() {
    Board board = new Board();
    assertEquals("bK", board.get("e8"));

    // Can't take same piece color
    Move move = new Move("e8", "e7", "bK");
    assertFalse(king.checkMove(board, move));

    // Normal move
    board.remove("e7");
    assertTrue(king.checkMove(board, move));

    // Can take other piece color
    board.put("e1", "bK");
    move = new Move("e1", "d1", "bK");
    assertTrue(king.checkMove(board, move));

    move = new Move("e1", "e2", "bK");
    assertTrue(king.checkMove(board, move));

    // Can't move diagonally
    move = new Move("e1", "d2", "bK");
    assertFalse(king.checkMove(board, move));

    // Can't move multiple spaces
    move = new Move("e1", "e3", "bK");
    assertFalse(king.checkMove(board, move));

    move = new Move("e1", "c1", "bK");
    assertFalse(king.checkMove(board, move));
  }
}
