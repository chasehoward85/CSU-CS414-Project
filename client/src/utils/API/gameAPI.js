import { getOriginalServerUrl, sendAPIRequest } from "./restfulAPI";

const gameBaseUrl = '/game';

// Temporary function while invitations are getting set up
export async function getInitialState() {
  const body = { requestType: 'getInitialState' };
  const url = getOriginalServerUrl();

  return sendAPIRequest(body, url);
}

export async function makeMoveRequest(board, userName, gameId, move) {
  const body = { requestType: 'makeMove', board: board, userName: userName, gameId: gameId, move };
  const url = getOriginalServerUrl();

  return sendAPIRequest(body, url);
}

export async function getGame(username){
  const body = {requestType:"gameDisplay", userName:username}
  const url = getOriginalServerUrl();

  return sendAPIRequest(body, url);
}

export async function getBoard(key) {
    const body = {requestType: "gameBoard", id: key};
    const url = getOriginalServerUrl();
	
    return sendAPIRequest(body, url);
}