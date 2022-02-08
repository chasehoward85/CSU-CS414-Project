package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public class Knight extends AbstractPiece {
  public Knight() {}

  @Override
  public boolean checkMove(Board board, Move move) {
    ArrayList<String> moveList = new ArrayList<String>();

    checkSquare(board, move.getSourceSquare(), moveList, 2, 1);
    checkSquare(board, move.getSourceSquare(), moveList, 1, 2);
    checkSquare(board, move.getSourceSquare(), moveList, 2, -1);
    checkSquare(board, move.getSourceSquare(), moveList, -1, 2);
    checkSquare(board, move.getSourceSquare(), moveList, -2, 1);
    checkSquare(board, move.getSourceSquare(), moveList, 1, -2);
    checkSquare(board, move.getSourceSquare(), moveList, -2, -1);
    checkSquare(board, move.getSourceSquare(), moveList, -1, -2);

    return moveList.contains(move.getTargetSquare());
  }
}
