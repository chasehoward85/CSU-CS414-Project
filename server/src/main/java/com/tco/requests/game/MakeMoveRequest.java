package com.tco.requests.game;

import com.tco.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeMoveRequest extends Request {
  private HashMap<String, String> board;
  private String userName;
  private Integer gameId;
  private Move move;
  private boolean valid;

  private final transient Logger log = LoggerFactory.getLogger(GetUserGamesRequest.class);
  private ArrayList<String> features;

  @Override
  public void prepareResponse() {
    this.userName = null;
    this.gameId = null;
    this.move = null;
    this.features = new ArrayList<>();
    features.add("makeMove");
    log.trace("buildResponse -> {}", this);
  }

  public Board getBoard() {
    return new Board(this.board);
  }

  public String getUserName() {
    return this.userName;
  }

  public Integer getGameId() {
    return this.gameId;
  }
  
  public Move getMove() {
    return this.move;
  }

  public void setBoard(Board board) {
    this.board = board.getRawBoard();
  }

  public void setBoard(HashMap<String, String> board) {
    this.board = board;
  }

  public void setValid(boolean valid) {
    this.valid = valid;
  }

  public MakeMoveRequest() {
    this.requestType = "makeMove";
  }

  private MakeMoveRequest(
    String userName,
    Integer gameId,
    Move move) {
      this();
      this.userName = userName;
      this.gameId = gameId;
      this.move = move;
  }

  public MakeMoveRequest(
    Board board,
    String userName,
    Integer gameId,
    Move move) {
      this(userName, gameId, move);
      this.board = board.getRawBoard();
  }
  
  public MakeMoveRequest(
    HashMap<String, String> board,
    String userName,
    Integer gameId,
    Move move) {
      this(userName, gameId, move);
      this.board = board;
  }
}
