package com.tco.controllers;

import com.tco.models.GameModel;
import com.tco.requests.Request;
import com.tco.requests.game.*;
import com.tco.misc.BadRequestException;
import com.tco.misc.GameValidator;
import java.util.HashMap;
import java.util.ArrayList;

public class GameController extends AbstractController {
  private GameModel model;
  private GameValidator validator;

  public GameController() {
    model = new GameModel();
    validator = new GameValidator();
  }

  // Test constructor since Mockito cannot mock constructors
  public GameController(GameModel testModel, GameValidator testValidator) {
      model = testModel;
      validator = testValidator;
  }

  public Request gameDisplayHandler (Request displayGame){
        GameDisplayRequest response =(GameDisplayRequest)displayGame;
        ArrayList<HashMap<String, String>> result = model.DisplayGame(response.getUsername());
        response.setGameDisplay(result);
        response.prepareResponse();

       return response;
    }

  public Request getUserGamesHandler(Request getUserGamesRequest) throws BadRequestException {
    GetUserGamesRequest response = (GetUserGamesRequest)getUserGamesRequest;
    response.setGames(this.model.getUserGames(response.getUserName()));
    response.prepareResponse();

    return response;
  }

  public Request getInitialStateHandler(Request getInitialStateRequest) throws BadRequestException {
    GetInitialStateRequest response = (GetInitialStateRequest)getInitialStateRequest;
    model.createGame(response);
    response.prepareResponse();

    return response;
  } 

  public Request getGameStateHandler(Request getGameStateRequest) throws BadRequestException {
    GetGameStateRequest response = (GetGameStateRequest) getGameStateRequest;
    response.setGameState(this.model.getGameState(response.getP1(), response.getP2()));
    response.prepareResponse();

    return response;
  }

  public Request makeMoveHandler(Request makeMoveRequest) throws BadRequestException {
    MakeMoveRequest response = (MakeMoveRequest)makeMoveRequest;

    Game currentGame = this.model.getGameById(response.getGameId());
    boolean valid = this.validator.validateAndMove(currentGame, response.getMove(), response.getUserName());
    response.prepareResponse();

    response.setValid(valid);

    if (valid) {
      this.model.updateGameMoveById(currentGame);
      response.setBoard(currentGame.getBoard());
    }

    char gameState = this.validator.checkGameEnd(currentGame.getBoard());

    if (gameState != 'n') {
      this.model.endGame(currentGame.getIntId());

      new UserController().updateStatsAfterGame(currentGame.getP1(), currentGame.getP2(), gameState);
    }

    return response;
  }

  	public Request gameBoardHandler(Request getBoardRequest) throws BadRequestException {
		GetBoardRequest request = (GetBoardRequest)getBoardRequest;

		HashMap<String, String> gameBoard = model.getGameBoardById(request.getKey());
    request.setGameBoard(gameBoard);

		request.prepareResponse();

		return request;
	}
}
