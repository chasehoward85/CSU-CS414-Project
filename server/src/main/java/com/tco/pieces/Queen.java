package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public class Queen extends AbstractPiece {
  public Queen() {}

  @Override
  public boolean checkMove(Board board, Move move) {
    ArrayList<String> moveList = new ArrayList<String>();

    for (int i = -1; i <= 1; ++i) {
      for (int j = -1; j <= 1; ++j) {
        checkLine(board, move.getSourceSquare(), moveList, i, j);
      }
    }

    return moveList.contains(move.getTargetSquare());
  }
}
