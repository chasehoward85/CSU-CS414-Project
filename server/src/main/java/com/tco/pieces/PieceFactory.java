package com.tco.pieces;

public abstract class PieceFactory {

  public static AbstractPiece createPiece(String pieceName) {
    AbstractPiece newPiece = new Pawn();

    switch (pieceName.charAt(1)) {
      case 'R':
        newPiece = new Rook();
        break;

      case 'N':
        newPiece = new Knight();
        break;

      case 'B':
        newPiece = new Bishop();
        break;

      case 'Q':
        newPiece = new Queen();
        break;
      
      case 'K':
        newPiece = new King();
        break;
    }

    return newPiece;
  }
}
