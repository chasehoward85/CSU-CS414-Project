package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public class Pawn extends AbstractPiece {
  public Pawn() {}

  @Override
  public boolean checkMove(Board board, Move move) {
    ArrayList<String> moveList = new ArrayList<String>();
    int moveDirection = (move.getPiece().charAt(0) == 'w') ? 1 : -1;

    checkSquare(board, move.getSourceSquare(), moveList, moveDirection, 0, false);

    // Diagonal moves if there is a piece to take
    checkSquare(board, move.getSourceSquare(), moveList, moveDirection, -1, true, true);
    checkSquare(board, move.getSourceSquare(), moveList, moveDirection, 1, true, true);

    // Double move off start
    if (move.getSourceSquare().charAt(1) == '2' || move.getSourceSquare().charAt(1) == '7') {
      checkSquare(board, move.getSourceSquare(), moveList, 2 * moveDirection, 0, false);
    }

    // Left en passant
    String enPassantTarget = String.format("%c%c", (char)((int)move.getSourceSquare().charAt(0) - 1), move.getSourceSquare().charAt(1));
    if (board.get(enPassantTarget) != null && board.get(enPassantTarget).charAt(0) != move.getPiece().charAt(0)) {
      checkSquare(board, move.getSourceSquare(), moveList, moveDirection, -1, false);
    }

    // Right en passant
    enPassantTarget = String.format("%c%c", (char)((int)move.getSourceSquare().charAt(0) + 1), move.getSourceSquare().charAt(1));
    if (board.get(enPassantTarget) != null && board.get(enPassantTarget).charAt(0) != move.getPiece().charAt(0)) {
      checkSquare(board, move.getSourceSquare(), moveList, moveDirection, 1, false);
    }

    return moveList.contains(move.getTargetSquare());
  }
}
