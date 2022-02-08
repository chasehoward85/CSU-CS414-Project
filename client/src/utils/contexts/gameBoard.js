import { createContext } from 'react';

export const gameBoardContext = createContext({
	board: {},
	setBoard: (b) => {board = b} 
});