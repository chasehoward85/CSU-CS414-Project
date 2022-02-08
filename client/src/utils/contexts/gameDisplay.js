import { createContext } from 'react';

export const gameDisplayContext = createContext({
	games: [],
	setGame: (users) => {games = users} 
});