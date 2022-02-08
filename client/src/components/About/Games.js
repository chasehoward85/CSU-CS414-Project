import React from 'react';
import GameCard from "./GameCard";

export default function Games(props) {
    let opponent = props.game.P1;
    let turn = props.game.Turn;
    let pTurn = "Opponent's turn";
    let yourTurn = false;
    let player;
    
    if(opponent == props.username ){
        opponent = props.game.P2;
    }

    if (turn != 2 && turn == 0 && props.username != props.game.P2){
        pTurn = "Your Turn!";
        yourTurn = true;
        player = false;
    } 
    else if(turn != 2 && turn == 1 && props.username != props.game.P1){
        pTurn = "Your Turn!";
        yourTurn = true;
        player = true;
    } 
    else if(turn >= 2){
       pTurn = "Game ended on " + props.game.Ended;
    }
     
    return (
        <GameCard pic={props.pic} game={props.game} turn={yourTurn} player={player} subTitle={opponent} text={pTurn} size={props.size} setTab={props.setTab} showMessage={props.showMessage}/>
    );
}
