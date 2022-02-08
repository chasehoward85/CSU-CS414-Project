import {userStatsRequest} from "../utils/API/userAPI";
import { useState } from 'react';

export function useStats() {
   const [wins, setWins] = useState(0);
   const [losses, setLosses] = useState(0);
   const [played, setPlayed] = useState(0);
   const [username, setUsername] = useState(0);
   async function getStats(username) {
       const response = await userStatsRequest(username);
       if (response){setWins(response.wins);
       setLosses(response.losses);
       setPlayed(response.played);}
   }
 
   return {getStats, wins, losses, played, setUsername};
}
 
 
