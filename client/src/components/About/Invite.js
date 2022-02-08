import React, {useContext} from "react";
import {Button, Table, FormGroup, Input} from "reactstrap";
import {useInvite} from "../../hooks/useInvite";
import {inviteContext} from "../../utils/contexts/invites";
import {usernameContext} from "../../utils/contexts/username";

export default function Invite(props) {
	const {username} = useContext(usernameContext);
	const inviteUse = useInvite(username, props.showMessage);
	const {invite} = useContext(inviteContext);

	return (
		<Table striped hover>
			<tbody>
				<tr>
					<td>
						<FormGroup>
							<Input name="usernameSearch" placeholder="Send an Invite" onChange={event => inviteUse.setRecevier(event.target.value)} />
						</FormGroup>
					</td>
					<td>
						<Button color="primary" onClick={() => inviteUse.submitInvite()} disabled={inviteUse.disabledButton}>
							Submit
						</Button>
					</td>
				</tr>
				{invite.length > 0 ? invite.map((sender) => 
					<tr>
						<td>{sender.Sender} is inviting you to a game</td>
						<td>
							<Button variant="success" className="btn-success" onClick={() => inviteUse.inviteOption(sender.Invite_ID, sender.Sender, true)} disabled={inviteUse.disabledButton}>&#10003;</Button>
							&nbsp;
							<Button variant="danger" className="btn-danger" onClick={() => inviteUse.inviteOption(sender.Invite_ID, sender.Sender, false)} disabled={inviteUse.disabledButton}>X</Button>
						</td>
					</tr>
				) : <h3>You have no pending invites. Ask your friends to send you some or send some yourself!</h3>}
			</tbody>
		</Table>
	);
}
