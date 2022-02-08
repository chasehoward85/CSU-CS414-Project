package com.tco.pieces;

import com.tco.requests.game.Move;
import com.tco.requests.game.Board;

import java.util.HashMap;
import java.util.ArrayList;

public abstract class AbstractPiece {
  private static final int aAsciiOffset = 97;
  private static final int hAsciiOffset = 104;
  private static final int oneAsciiOffset = 49;
  private static final int eightAsciiOffset = 56;

  public abstract boolean checkMove(Board board, Move move);

  protected void checkLine(Board board, String sourceSquare, ArrayList<String> moveList, int rowStep, int colStep) {
    String currentPiece = board.get(sourceSquare);
    int stepCount = 0;
    boolean step = true;
    int sourceRow = (int)sourceSquare.charAt(1);
    int sourceCol = (int)sourceSquare.charAt(0);

    while (step && stepCount <= 7) {
      stepCount += 1;
      int currentRow = sourceRow + (stepCount * rowStep);
      int currentCol = sourceCol + (stepCount * colStep);
      String potentialSquare = rowColToString(currentRow, currentCol);


      if (validSquare(currentRow, currentCol) && pieceCanTake(currentPiece, board.get(potentialSquare))) {
        moveList.add(potentialSquare);
        step = board.get(potentialSquare) == null;
      } else {
        step = false;
      }
    }
  }

  protected void checkSquare(Board board, String sourceSquare, ArrayList<String> moveList, int rowOffset, int colOffset) {
    this.checkSquare(board, sourceSquare, moveList, rowOffset, colOffset, true);
  }

  protected void checkSquare(Board board, String sourceSquare, ArrayList<String> moveList, int rowOffset, int colOffset, boolean canTakePiece) {
    this.checkSquare(board, sourceSquare, moveList, rowOffset, colOffset, canTakePiece, false);
  }

  protected void checkSquare(Board board, String sourceSquare, ArrayList<String> moveList, int rowOffset, int colOffset, Boolean canTakePiece, boolean pieceMustTake) {
    String currentPiece = board.get(sourceSquare);
    int targetRow = (int)sourceSquare.charAt(1) + rowOffset;
    int targetCol = (int)sourceSquare.charAt(0) + colOffset;
    String potentialSquare = rowColToString(targetRow, targetCol);

    if (validSquare(targetRow, targetCol) && pieceCanTake(currentPiece, board.get(potentialSquare), canTakePiece, pieceMustTake)) {
      moveList.add(potentialSquare);
    }
  }

  private boolean validSquare(int row, int col) {
    boolean validRow = row >= AbstractPiece.oneAsciiOffset && row <= AbstractPiece.eightAsciiOffset;
    boolean validCol = col >= AbstractPiece.aAsciiOffset && col <= AbstractPiece.hAsciiOffset;
    return validRow && validCol;
  }

  protected boolean pieceCanTake(String currentPiece, String targetPiece) {
    return this.pieceCanTake(currentPiece, targetPiece, true);
  }

  protected boolean pieceCanTake(String currentPiece, String targetPiece, boolean canTakePiece) {
    return this.pieceCanTake(currentPiece, targetPiece, canTakePiece, false);
  }

  protected boolean pieceCanTake(String currentPiece, String targetPiece, boolean canTakePiece, boolean pieceMustTake) {
    return (!pieceMustTake && targetPiece == null) || (targetPiece != null && canTakePiece && currentPiece.charAt(0) != targetPiece.charAt(0));
  }

  protected String rowColToString(int row, int col) {
    return String.format("%s%s", (char)col, (char)row);
  }
}
