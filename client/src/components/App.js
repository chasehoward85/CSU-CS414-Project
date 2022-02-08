import React, { useState } from 'react';
import { SnackbarProvider, useSnackbar } from 'notistack';
import { usernameContext } from '../utils/contexts/username';
import {inviteContext} from "../utils/contexts/invites";
import { gameDisplayContext } from '../utils/contexts/gameDisplay';
import {gameBoardContext} from "../utils/contexts/gameBoard";
import Page from './Page';

export default function App() {
    return (
        <SnackbarProvider maxSnack={3}>
            <HookCaller />
        </SnackbarProvider>
    );
}

export const HookCaller = () => {
    const { enqueueSnackbar } = useSnackbar();
    const [ username, setUsername ] = useState('');
    const [invite, setInvite] = useState("");
    const [games, setGame] = useState("");
    const [board, setBoard] = useState({});

    function showMessage(message, variant = "info") {
        enqueueSnackbar(message, { variant: variant })
    }

    return <usernameContext.Provider value={{ username: username, setUsername: setUsername }}>
                <inviteContext.Provider value={{invite: invite, setInvite: setInvite}}>
                    <gameDisplayContext.Provider value={{games: games, setGame: setGame}}>
                        <gameBoardContext.Provider value={{board: board, setBoard: setBoard}}>
                            <Page showMessage={showMessage} />
                        </gameBoardContext.Provider>
                    </gameDisplayContext.Provider>
                </inviteContext.Provider>
            </usernameContext.Provider>;
};
