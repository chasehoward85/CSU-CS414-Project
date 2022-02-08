import React from 'react';
import GameCard from "./GameCard";

export default function User(props) {
    return (
        <GameCard pic={props.pic} title={props.username} text={props.email} size={props.size} />
    );
}
