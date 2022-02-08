package com.tco.services;

import com.tco.services.AbstractService;
import com.tco.controllers.GameController;
import com.tco.requests.game.*;
import com.tco.requests.Request;
import com.tco.misc.BadRequestException;

import java.lang.reflect.InvocationTargetException;
import java.lang.NoSuchMethodException;
import java.lang.IllegalAccessException;
import java.io.IOException;

import static spark.Spark.*;

public class GameService extends AbstractService {
  public GameService() {
      controller = new GameController();
  }

  @Override
  public void initAndServe() {
    path("/api", () -> {
      // start coding here
      post("/getUserGames", (req, res) -> processHttpRequest(req, res, GetUserGamesRequest.class));
      post("/getInitialState", (req, res) -> processHttpRequest(req, res, GetInitialStateRequest.class));
      post("/makeMove", (req, res) -> processHttpRequest(req, res, MakeMoveRequest.class));
      post("/gameDisplay", (req, res) -> processHttpRequest(req, res, GameDisplayRequest.class));
      post("/getGameState", (req, res) -> processHttpRequest(req, res, GetGameStateRequest.class));
      post("/gameBoard", (req, res) -> processHttpRequest(req, res, GetBoardRequest.class));
    });
  }
}
