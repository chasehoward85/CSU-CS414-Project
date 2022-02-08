import { createContext } from 'react';

export const usernameContext = createContext({
	username: '',
	setName: (name) => {username = name} 
});