package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public class King extends AbstractPiece {
  public King() {}

  @Override
  public boolean checkMove(Board board, Move move) {
    ArrayList<String> moveList = new ArrayList<String>();

    checkSquare(board, move.getSourceSquare(), moveList, 1, 0);
    checkSquare(board, move.getSourceSquare(), moveList, 0, 1);
    checkSquare(board, move.getSourceSquare(), moveList, -1, 0);
    checkSquare(board, move.getSourceSquare(), moveList, 0, -1);

    return moveList.contains(move.getTargetSquare());
  }
}
