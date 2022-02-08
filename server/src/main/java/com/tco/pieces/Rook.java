package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public class Rook extends AbstractPiece {
  public Rook() {}

  @Override
  public boolean checkMove(Board board, Move move) {
    ArrayList<String> moveList = new ArrayList<String>();

    checkLine(board, move.getSourceSquare(), moveList, 1, 0);
    checkLine(board, move.getSourceSquare(), moveList, 0, 1);
    checkLine(board, move.getSourceSquare(), moveList, -1, 0);
    checkLine(board, move.getSourceSquare(), moveList, 0, -1);

    return moveList.contains(move.getTargetSquare());
  }
}
