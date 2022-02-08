package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestPawn {
  AbstractPiece pawn;

  @BeforeEach
  public void beforeEach() {
    pawn = new Pawn();
  }

  @Test
  @DisplayName("PAwn instatiates")
  public void testPawnInstatiates() {
    assertNotNull(pawn);
  }

  @Test
  @DisplayName("Pawn valdiates moves")
  public void testPawnCanMove() {
    Board board = new Board();
    assertEquals("wP", board.get("a2"));
    assertEquals("bP", board.get("a7"));

    // Normal move
    Move move = new Move("a2", "a3", "wP");
    assertTrue(pawn.checkMove(board, move));

    move = new Move("a7", "a6", "bP");
    assertTrue(pawn.checkMove(board, move));

    // Double move on starting square
    move = new Move("a2", "a4", "wP");
    assertTrue(pawn.checkMove(board, move));

    move = new Move("a7", "a5", "bP");
    assertTrue(pawn.checkMove(board, move));

    board.put("d4", "wP");
    board.put("e4", "bP");

    // Can't move backwards
    move = new Move("d4", "d3", "wP");
    assertFalse(pawn.checkMove(board, move));

    move = new Move("e4", "e5", "bP");
    assertFalse(pawn.checkMove(board, move));
    
    // Can't move two squares when not in starting square
    move = new Move("d4", "d6", "wP");
    assertFalse(pawn.checkMove(board, move));

    move = new Move("e4", "e2", "bP");
    assertFalse(pawn.checkMove(board, move));

    // En passant
    move = new Move("d4", "e5", "wP");
    assertTrue(pawn.checkMove(board, move));

    move = new Move("e4", "d3", "bP");
    assertTrue(pawn.checkMove(board, move));
  }
}
