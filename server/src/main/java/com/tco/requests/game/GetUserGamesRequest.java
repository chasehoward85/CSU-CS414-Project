package com.tco.requests.game;

import com.tco.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserGamesRequest extends Request {

  private String userName;
  private ArrayList<Game> games;
  private final transient Logger log = LoggerFactory.getLogger(GetUserGamesRequest.class);
  private ArrayList<String> features;

  @Override
  public void prepareResponse() {
    this.userName = null;
    this.features = new ArrayList<>();
    features.add("getUserGames");
    log.trace("buildResponse -> {}", this);
  }

  public String getUserName() {
    return this.userName;
  }

  public void setGames(ArrayList<Game> games) {
    this.games = games;
  }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

  public GetUserGamesRequest() {
    this.requestType = "getUserGames";
  }

  public GetUserGamesRequest(String userName) {
    this();
    this.userName = userName;
  }

  public GetUserGamesRequest(int gameId, String P1, String P2) {
    this();
    this.games = new ArrayList<Game>();
    this.games.add(new Game(gameId, P1, P2));
  }

  public ArrayList<Game> getGames() {
    return this.games;
  }

  public void setUserName(String name) {
    this.userName = name;
  }

  public boolean validFeature(String feature){
    return features.contains(feature);
  }
}
