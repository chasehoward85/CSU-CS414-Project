import React from 'react';
import { Row, Col } from 'reactstrap';
import {useStats} from "../../hooks/useStats";

export default function Statistics(props) {
const username = props.username;
const useStatsFunction = useStats();
useStatsFunction.getStats(username);

    return (
        <Row>
           <Col>Wins:{useStatsFunction.wins}</Col>
           <Col>Losses:{useStatsFunction.losses}</Col>
           <Col>Games Played:{useStatsFunction.played}</Col>
        </Row>
    );
}