import React, { useContext, useEffect } from 'react';
import { Container, Row, Col } from 'reactstrap';
import { CLIENT_TEAM_NAME } from '../../utils/constants';
import Games from "./Games";
import User from "./User";
import Statistics from "./Statistics";
import {userData, gameData} from "./profileInfo";
import {usernameContext} from '../../utils/contexts/username';
import { gameDisplayContext } from '../../utils/contexts/gameDisplay';
import imagePath from '../../static/images/PlaceholderTeam.jpg'


export default function About(props) {
    const {username} = useContext(usernameContext);
    const {games} = useContext(gameDisplayContext);

    return (
        <Container>
            <Row>
                <Col>
                    <h2>{CLIENT_TEAM_NAME} xGame: Extinction Chess</h2>
                </Col>
            </Row>
            <Row>
                
                <User username={username} email={userData.email} pic={userData.imagePath} size={{md: 4}}/>
                <Col>
                    <h4>USER STATISTICS</h4>
                    <Statistics username={username}/>
                </Col>
            </Row>
            <Row>
                {games.length > 0 ? games.map((game) =>
                    <Games key={game.Game_ID} game={game} pic={imagePath} username={username} size={{sm: 2}} setTab={props.setTab} showMessage={props.showMessage}/>
                ): <h3>You are not currently playing any games. Send or accept some invites to start one!</h3>}
                
            </Row>
        </Container>
    );
}
