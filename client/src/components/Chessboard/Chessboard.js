import ChessBoard from "chessboardjsx";
import React, { useCallback, useContext, useEffect, useState } from 'react';
import { Button, Container, Row, Col, Modal } from 'reactstrap';
import { makeMoveRequest } from "../../utils/API/gameAPI";
import Rules from "./Rules";
import { useToggle } from '../../hooks/useToggle';
import { gameBoardContext } from "../../utils/contexts/gameBoard";
import {useGame} from "../../hooks/useGame";

export default function Chessboard(props) {
    const {board, setBoard} = useContext(gameBoardContext);
    const gameUse = useGame();
    const [showRules, toggleShow] = useToggle(false);

    const boardResponseHandler = useCallback(async (moveObj) => {
        const response = await makeMoveRequest(board, props.user, parseInt(props.game), moveObj)
        
        if(!response.valid) {
            props.showMessage("Move is invalid. Please try another move", "error")
        }
        else {
            gameUse.goBackToProfile(props.setTab);
            setBoard(response.board);
        }
    }, [props.game, board, props.user]);

    const handleMove = (moveObj) => {
        boardResponseHandler(moveObj);
        return 'trash';
    };

    return (
        <Container>
            <Row>
                <Row>
                    <Col>
                    <Button color="primary" onClick={toggleShow}>
                        Rules
                    </Button>
                    <Modal isOpen={showRules}>
                        <Button color="primary" onClick={toggleShow}>Click here to Close</Button>
                        <Rules/>
                    </Modal>
                    </Col>
                </Row>
                <Col>
                <div style={{
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                }}>
                    <ChessBoard onDrop={handleMove} position={board} width={650} height={650} />
                </div>
                </Col>
            </Row>
        </Container>
    )

}

