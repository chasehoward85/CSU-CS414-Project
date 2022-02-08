package com.tco.pieces;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TestPieceFactory {
  @Test
  @DisplayName("Factory should return the correct type")
  public void testPieceFactory() {
    AbstractPiece testPiece = PieceFactory.createPiece("wP");
    assertTrue(testPiece.getClass() == Pawn.class);

    testPiece = PieceFactory.createPiece("bR");
    assertTrue(testPiece.getClass() == Rook.class);

    testPiece = PieceFactory.createPiece("wN");
    assertTrue(testPiece.getClass() == Knight.class);

    testPiece = PieceFactory.createPiece("bB");
    assertTrue(testPiece.getClass() == Bishop.class);

    testPiece = PieceFactory.createPiece("wQ");
    assertTrue(testPiece.getClass() == Queen.class);

    testPiece = PieceFactory.createPiece("bK");
    assertTrue(testPiece.getClass() == King.class);
  }
}
