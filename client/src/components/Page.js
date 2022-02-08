import React, { useEffect, useState, useContext, useCallback } from 'react';
import { Row, Nav, NavItem, NavLink, TabContent, TabPane } from 'reactstrap';
import Header from './Margins/Header';
import Footer from './Margins/Footer';
import About from './About/Profile';
import Invite from './About/Invite';
import { LOG } from '../utils/constants';
import { getOriginalServerUrl, sendAPIRequest } from '../utils/API/restfulAPI';
import SignIn from './SignIn/SignIn';
import classnames from 'classnames';
import Chessboard from './Chessboard/Chessboard';
import { useInvite } from '../hooks/useInvite';
import { usernameContext } from '../utils/contexts/username';
import {useGame} from "../hooks/useGame";

export default function Page(props) {
	const [serverSettings, processServerConfigSuccess] = useServerSettings(props.showMessage);
	const [activeTab, setActiveTab] = useState('1');
	const { username } = useContext(usernameContext);
	const inviteUse = useInvite(username, props.showMessage);
	const [unregister, changeUnregister] = useState(false);
	const [hidden, setHidden] = useState(true);
	const displayGame = useGame();
	const [gameID, setGameID] = useState();

	const setTab = useCallback((tab, id="") => {
	  if(activeTab !== tab) setActiveTab(tab);

		if(tab == '4' && id != "") {
			setGameID(id);
		}
	}, [activeTab, setGameID]);

	function setUnregister(unregister) {
		if(unregister) {
			changeUnregister(true);
			setTab("1");
			alert("Unregistering will delete your account and all associated information");
		}
	}

	useEffect(() => {
		if (username) {
			setHidden(false);
			setTab("2");
			inviteUse.displayInvite();
			displayGame.displayGame();
			setInterval(inviteUse.haveNewInvites, 5000);
			setInterval(displayGame.displayGame, 5000);
		}
	}, [username]);

	return (
		<>
			<Header />
				<div className="body">
					<Nav tabs hidden={hidden}>
						<NavItem hidden={true}>
							<NavLink
								className={classnames({ active: activeTab === '1' })}
								onClick={() => { setTab('1'); }}
							>
								Sign In
							</NavLink>
						</NavItem>
						<NavItem hidden={hidden}>
							<NavLink
								className={classnames({ active: activeTab === '2' })}
								onClick={() => { setTab('2'); }}
							>
								My Profile
							</NavLink>
						</NavItem>
						<NavItem hidden={hidden}>
							<NavLink
								className={classnames({active: activeTab === '3'})}
								onClick={() => { setTab('3');}}
							>
								Invitations
							</NavLink>
						</NavItem>
						<NavItem hidden={true}>
							<NavLink
								className={classnames({ active: activeTab === '4' })}
								onClick={() => { setTab('4'); }}
							>
								Game
							</NavLink>
						</NavItem>
					</Nav>

					<TabContent data-testid={'tabList'} activeTab={activeTab}>
						<TabPane tabId="1">
							<SignIn showMessage={props.showMessage} unregister={unregister}/>
						</TabPane>
						<TabPane tabId="2">
							<About setTab={setTab} showMessage={props.showMessage}/>
						</TabPane>
						<TabPane tabId="3">
							<Invite showMessage={props.showMessage}/>
						</TabPane>
						<TabPane tabId="4">
							<Chessboard game={gameID} user={username} setTab={setTab} showMessage={props.showMessage}/>
						</TabPane>
					</TabContent>
				</div>
			<Footer
				serverSettings={serverSettings}
				processServerConfigSuccess={processServerConfigSuccess}
				setUnregister={setUnregister}
			/>
		</>
	)
}

function useServerSettings(showMessage) {
	const [serverUrl, setServerUrl] = useState(getOriginalServerUrl());
	const [serverConfig, setServerConfig] = useState(null);

	useEffect(() => {
		sendConfigRequest();
	}, []);

	function processServerConfigSuccess(config, url) {
		LOG.info("Switching to Server:", url);
		setServerConfig(config);
		setServerUrl(url);
	}

	async function sendConfigRequest() {
		const configResponse = await sendAPIRequest({ requestType: "config" }, serverUrl);
		if (configResponse) {
			processServerConfigSuccess(configResponse, serverUrl);
		} else {
			setServerConfig(null);
			showMessage(`Config request to ${serverUrl} failed. Check the log for more details.`, "error");
		}
	}

	return [{ serverUrl: serverUrl, serverConfig: serverConfig }, processServerConfigSuccess];
}
