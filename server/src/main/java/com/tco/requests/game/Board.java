package com.tco.requests.game;

import java.util.HashMap;
import java.util.Set;

public class Board {
  private HashMap<String, String> board;

  public Board() {
    initializeBoard();
  }

  public Board(HashMap<String, String> board) {
    this.board = board;
  }

  private int getAOffset() {
    return 97;
  }

  private void putEndRow(String color) {
    char row = color == "w" ? '1' : '8';
    final char[] PIECES = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};

    for (int i = 0; i < 8; ++i) {
      String key = Character.toString(this.getAOffset() + i) + row;
      String value = color + PIECES[i];
      this.board.put(key, value);
    }
  }

  private void putPawnRow(String color) {
    char row = color == "w" ? '2' : '7';

    for (int i = 0; i < 8; ++i) {
      String key = Character.toString(this.getAOffset() + i) + row;
      String value = color + "P";
      this.board.put(key, value);
    }
  }

  private void initializeBoard() {
    this.board = new HashMap<String, String>();

    putEndRow("w");

    putPawnRow("w");

    putPawnRow("b");

    putEndRow("b");
  }

  public String put(String squareString, String pieceString) {
    return this.board.put(squareString, pieceString);
  }

  public String get(String squareString) {
    return this.board.get(squareString);
  }

  public String remove(String squareString) {
    return this.board.remove(squareString);
  }

  public Set<String> keySet() {
    return this.board.keySet();
  }

  // For testing
  public HashMap<String, String> getRawBoard() {
    return this.board;
  }
}
