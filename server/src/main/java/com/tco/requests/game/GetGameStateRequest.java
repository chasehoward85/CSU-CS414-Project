package com.tco.requests.game;

import com.tco.requests.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetGameStateRequest extends Request{
    private final transient Logger log = LoggerFactory.getLogger(GetUserGamesRequest.class);
    private ArrayList<String> features;
    private String p1;
    private String p2;
    private ArrayList<Game> gameState; 

    @Override
    public void prepareResponse() {
        this.p1 = null;
        this.p2 = null;
        this.features = new ArrayList<>();
        features.add("getGameState");
        log.trace("buildResponse -> {}", this);
    }

    public GetGameStateRequest() {
        this.requestType = "getGameState";
    }

    public GetGameStateRequest(String p1, String p2) {
        this();
        this.p1 = p1;
        this.p2 = p2;
    }

    public void setGameState(ArrayList<Game> gameState) {
        this.gameState = gameState;
    }
    public boolean validFeature(String feature) {
        return features.contains(feature);
    }

    public String getP1() {
        return this.p1;
    }

    public String getP2() {
        return this.p2;
    }

    public ArrayList<Game> getGameState() {
        return this.gameState;
    }
}
