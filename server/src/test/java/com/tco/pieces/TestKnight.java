package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestKnight {
  AbstractPiece knight;

  @BeforeEach
  public void beforeEach() {
    knight = new Knight();
  }

  @Test
  @DisplayName("Knight instatiates")
  public void testKnightInstatiates() {
    assertNotNull(knight);
  }

  @Test
  @DisplayName("Knight validates moves")
  public void testKnightCanMove() {
    Board board = new Board();
    assertEquals("wN", board.get("b1"));

    // Can't take same piece color
    Move move = new Move("b1", "d2", "bN");
    assertFalse(knight.checkMove(board, move));

    // Normal move
    board.remove("d2");
    assertTrue(knight.checkMove(board, move));

    move = new Move("b1", "c3", "bN");
    assertTrue(knight.checkMove(board, move));

    move = new Move("b1", "a3", "bN");
    assertTrue(knight.checkMove(board, move));

    // Can take other piece color
    board.put("b8", "wN");
    move = new Move("b8", "d7", "bN");
    assertTrue(knight.checkMove(board, move));

    // Can't move diagonally
    move = new Move("b8", "d2", "bN");
    assertFalse(knight.checkMove(board, move));

    // Can't move single spaces
    move = new Move("b8", "b7", "bN");
    assertFalse(knight.checkMove(board, move));

    move = new Move("b8", "a8", "bN");
    assertFalse(knight.checkMove(board, move));

    move = new Move("b8", "a4", "bN");
    assertFalse(knight.checkMove(board, move));

    move = new Move("b8", "c7", "bN");
    assertFalse(knight.checkMove(board, move));
  }
}
