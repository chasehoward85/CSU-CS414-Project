import {useContext} from "react";
import { getGame, getBoard } from "../utils/API/gameAPI";
import { gameDisplayContext } from "../utils/contexts/gameDisplay";
import { usernameContext } from '../utils/contexts/username';
import {gameBoardContext} from "../utils/contexts/gameBoard";

export function useGame(){
    const {setGame} = useContext(gameDisplayContext);
    const {username} = useContext(usernameContext);
    const {setBoard} = useContext(gameBoardContext);

    async function displayGame(){
        const games = await getGame(username);
        setGame(games.setDisplay);
    }
    
    async function getGameBoard(id) {
        const board = await getBoard(id);
        setBoard(board.board);
    }

    function goBackToProfile(setTab) {
        setTimeout(setTab, 10000, "2");
    }

    return {displayGame, getGameBoard, goBackToProfile}
}