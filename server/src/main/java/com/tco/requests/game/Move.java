package com.tco.requests.game;

public class Move {
  private String sourceSquare;
  private String targetSquare;
  private String piece;

  public Move(String sourceSquare, String targetSquare, String piece) {
    this.sourceSquare = sourceSquare;
    this.targetSquare = targetSquare;
    this.piece = piece;
  }

  public String getSourceSquare() {
    return this.sourceSquare;
  }

  public String getTargetSquare() {
    return this.targetSquare;
  }

  public String getPiece() {
    return this.piece;
  }
}
