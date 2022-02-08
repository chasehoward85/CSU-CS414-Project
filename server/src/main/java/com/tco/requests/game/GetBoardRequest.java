package com.tco.requests.game;

import com.tco.requests.Request;
import com.tco.requests.game.Board;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetBoardRequest extends Request {
    private final transient Logger log = LoggerFactory.getLogger(GetBoardRequest.class);
	private String id;
    private ArrayList<String> features;
    private HashMap<String, String> board; 

    @Override
    public void prepareResponse() {
        this.id = null;
        this.features = new ArrayList<>();
        features.add("gameBoard");
        log.trace("buildResponse -> {}", this);
    }

    public GetBoardRequest() {
        this.requestType = "gameBoard";
    }

    public GetBoardRequest(String key) {
        this();
        this.id = key;
    }

    public void setGameBoard(HashMap<String, String> game) {
        this.board = game;
    }

    public boolean validFeature(String feature) {
        return features.contains(feature);
    }

    public String getKey() {
        return this.id;
    }

    public HashMap<String, String> getGameBoard() {
        return this.board;
    }
}
