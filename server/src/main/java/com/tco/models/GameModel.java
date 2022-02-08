package com.tco.models;

import com.tco.requests.game.*;

import com.tco.requests.Database;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Random;

public class GameModel {
	public GameModel() {
		super();
	}

	public ArrayList<Game> getUserGames(String userName) {
		String stmntString = String.format("SELECT * FROM Game_Information\n"
		+ "WHERE \"%s\" IN (P1, P2)", userName);

		ArrayList<HashMap<String, String>> result = Database.queryDB(stmntString);

		return this.constructGames(result);
	}

	private ArrayList<Game> constructGames(ArrayList<HashMap<String, String>> result) {
		ArrayList<Game> games = new ArrayList<Game>();
		
		for (HashMap<String, String> rawGame : result) {
			Board board = new Board(new Gson().fromJson(rawGame.get("Board"), HashMap.class));

			games.add(new Game(
				Integer.parseInt(rawGame.get("Game_ID")),
				rawGame.get("P1"),
				rawGame.get("P2"),
				board,
				rawGame.get("Turn").equals("1"),
				parseDate(rawGame.get("Started")),
				parseDate(rawGame.get("Ended"))
			));
		}

		return games;
	}

	private LocalDate parseDate(String dateString) {
		if (dateString.equals("0000-00-00 00:00:00")) {
			return null;
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss[.S]");

		return LocalDate.parse(dateString, formatter);
	}

	public ArrayList<Game> getGameState(String p1, String p2) {

		String getGameState = String.format("SELECT * FROM Game_Information\n"
		+ "WHERE P1=\"%s\" AND P2=\"%s\"",p1, p2);		
		ArrayList<HashMap<String, String>> result = Database.queryDB(getGameState);
		return this.constructGames(result);
	}

	public boolean createGame(GetInitialStateRequest game) {
		Integer id = game.getID();
		String p1 = game.getP1();
		String p2 = game.getP2();
		boolean accepted = game.getAccepted();
		String board = new Board().getRawBoard().toString();

		int value = accepted ? 1 : 0;

		String updateInvite = "UPDATE Game_Invitation SET Accepted=" + value + " WHERE Invite_ID=" + id + ";";

		if(accepted) {
			try {
				Random rand = new Random();

				String insertGame = "INSERT INTO Game_Information(Game_ID,P1,P2,Board,Turn) VALUES (" + id + ",\"" + p1 + "\",\"" + p2 + "\",\""
				+ board + "\"," + rand.nextInt(2) + ");";

				ArrayList<HashMap<String, String>> result = Database.queryDB(insertGame);
				ArrayList<HashMap<String, String>> result2 = Database.queryDB(updateInvite);
				return true;
			} catch(Exception e) {
				return false;
			}
		}
		else {
			try {
				ArrayList<HashMap<String, String>> result = Database.queryDB(updateInvite);
				return true;
			} catch(Exception e) {
				return false;
			}
		}
	}

	public ArrayList<HashMap<String,String>> DisplayGame(String username){
		String displayQuery = String.format("SELECT Game_ID, P1, P2, Ended, Turn FROM Game_Information WHERE" +  "\"%s\" IN (P1, P2)", username);
		ArrayList<HashMap<String, String>> result = Database.queryDB(displayQuery);
		return result;
	}
	
	public Game getGameById(Integer gameId) {
		String getGameState = String.format("SELECT * FROM Game_Information\n"
		+ "WHERE Game_ID=%d", gameId);
		ArrayList<HashMap<String, String>> result = Database.queryDB(getGameState);

		return constructGames(result).get(0);
	}

	public HashMap<String, String> getGameBoardById(String gameId) {
		String getGameState = "SELECT Board FROM Game_Information WHERE Game_ID=" + gameId + ";";
		ArrayList<HashMap<String, String>> result = Database.queryDB(getGameState);

		Board board = new Board(new Gson().fromJson(result.get(0).get("Board"), HashMap.class));

		return board.getRawBoard();
	}

	public void updateGameMoveById(Game updatedGame) {
		String newGameState = String.format(
			"UPDATE Game_Information " +
			"SET Game_ID=%d, Board=\"%s\", Turn=%d " +
			"WHERE Game_ID=%d;",
			updatedGame.getIntId(), updatedGame.getBoard().getRawBoard().toString(), updatedGame.getTurn() ? 1 : 0, updatedGame.getIntId()
		);

		Database.queryDB(newGameState);
	}

	public void endGame(int gameId) {
		String newGameState = String.format(
			"UPDATE Game_Information " +
			"SET Turn=2 " +
			"WHERE Game_ID=%d;",
			gameId
		);

		Database.queryDB(newGameState);

	}
}
