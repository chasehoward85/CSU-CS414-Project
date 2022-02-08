package com.tco.requests.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class Game {

  private int gameId;
  private String P1;
  private String P2;
  private Board board;
  private boolean turn;
  private LocalDate startDate;
  private LocalDate endDate;

  public Game(int id, String P1, String P2) {
    this.gameId = id;
    this.P1 = P1;
    this.P2 = P2;
    this.turn = true;
    this.startDate = LocalDate.now();
    this.board = new Board();
  }

  public Game(
    int id,
    String P1,
    String P2,
    Board board,
    boolean turn,
    LocalDate startDate,
    LocalDate endDate
    ) {
      this.gameId = id;
      this.P1 = P1;
      this.P2 = P2;
      this.board = board;
      this.turn = turn;
      this.startDate = startDate;
      this.endDate = endDate;
      this.board = board;
  }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

  public String getId() {
    return String.valueOf(this.gameId);
  }

  public int getIntId() {
    return this.gameId;
  }

  public String getP1() {
    return this.P1;
  }

  public String getP2() {
    return this.P2;
  }

  public Board getBoard() {
    return this.board;
  } 

  public boolean getTurn() {
    return this.turn;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public void setTurn(Boolean turn) {
    this.turn = turn;
  }
}
