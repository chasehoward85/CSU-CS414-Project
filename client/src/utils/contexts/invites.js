import { createContext } from 'react';

export const inviteContext = createContext({
	invite: [],
	setInvite: (senders) => {invite = senders} 
});