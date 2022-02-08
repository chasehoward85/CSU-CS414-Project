package com.tco.misc;

import com.tco.pieces.AbstractPiece;
import com.tco.pieces.PieceFactory;
import com.tco.requests.game.Move;
import com.tco.requests.game.Game;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.Collection;
import java.lang.Math;

public class GameValidator {
  public GameValidator() {};

  // TODO change board to Game when we get the game from the GameModel
  public boolean validateAndMove(Game game, Move move, String userName) {
    System.out.println(game.getTurn());
    System.out.println(game.getP1());
    System.out.println(game.getP2());

    if (game.getTurn() && userName.equals(game.getP1()) || !game.getTurn() && userName.equals(game.getP2())) {
      System.out.println("It is not this user's turn.");
      return false;
    }

    char pieceColor = move.getPiece().charAt(0);

    if (pieceColor == 'w' && !userName.equals(game.getP1()) || pieceColor == 'b' && !userName.equals(game.getP2())) {
      System.out.println("The piece does not belong to this user");
      return false;
    }

    Board board = game.getBoard();

    if (!pieceCanMove(board, move)) {
      System.out.println("The piece cannot move to this space");
      return false;
    }

    board.put(move.getTargetSquare(), move.getPiece());
    board.remove(move.getSourceSquare());

    game.setBoard(board);
    game.setTurn(!game.getTurn());

    System.out.println("Valid Move");

    return true;
  }

  private boolean pieceCanMove(Board board, Move move) {
    boolean isValid = true;

    AbstractPiece currentPiece = PieceFactory.createPiece(move.getPiece());

    isValid = currentPiece.checkMove(board, move);

    if (isValid && move.getPiece().charAt(1) == 'P') {
      checkEnPassant(board, move);
    }
    
    return isValid;
  }

  private void checkEnPassant(Board board, Move move) {
    int sourceRow = (int)move.getSourceSquare().charAt(1);
    int sourceCol = (int)move.getSourceSquare().charAt(0);
    int targetRow = (int)move.getTargetSquare().charAt(1);
    int targetCol = (int)move.getTargetSquare().charAt(0);

    if (Math.abs(sourceRow - targetRow) == 1 && Math.abs(sourceCol - targetCol) == 1) {
      board.remove(String.format("%s%s", (char)targetCol, (char)sourceRow));
    }
  }

  public char checkGameEnd(Board board) {
    Collection<String> allPieces = board.getRawBoard().values();

    if (
      !allPieces.contains("wK") ||
      !allPieces.contains("wQ") ||
      !allPieces.contains("wB") ||
      !allPieces.contains("wN") ||
      !allPieces.contains("wR") ||
      !allPieces.contains("wP")) {
        return 'b';
    } else if (
      !allPieces.contains("bK") ||
      !allPieces.contains("bQ") ||
      !allPieces.contains("bB") ||
      !allPieces.contains("bN") ||
      !allPieces.contains("bR") ||
      !allPieces.contains("bP")) {
      return 'w';
    }

    return 'n';
  }
}
