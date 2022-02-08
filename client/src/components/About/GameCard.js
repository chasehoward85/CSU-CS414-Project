import React from 'react';
import {CardImg, CardBody, CardTitle, CardSubtitle, CardText, Col, Card} from 'reactstrap';
import {useGame} from '../../hooks/useGame';

export default function GameCard(props) {
    const gameUse = useGame();
    let cursor = props.turn ? "pointer" : "default"

    return (
        <Col className="mb-4" {...props.size}>
            <Card style={{cursor: cursor}} onClick={() => props.turn ? gameUse.getGameBoard(props.game.Game_ID).then(() => {props.setTab("4", parseInt(props.game.Game_ID)); props.showMessage(props.player ? "You are black" : "You are white")}) : props.showMessage("It isn't your turn", "error")}>
                <CardImg top width="100%" src={props.pic} />
                <CardBody>
                    <CardTitle tag="h5">{props.title}</CardTitle>
                    <CardSubtitle tag="h6" className="mb-2 text-muted">{props.subTitle}</CardSubtitle>
                    <CardText>{props.text}</CardText>
                </CardBody>
            </Card>
        </Col>
    );
}