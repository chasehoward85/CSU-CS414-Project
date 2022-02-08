import {useState, useContext} from "react";
import {inviteContext} from "../utils/contexts/invites";
import {inviteRequest} from "../utils/API/sendInviteAPI";
import {getInvite} from "../utils/API/GetInvite";
import {useInviteOption} from "../utils/API/useInviteOptionAPI"

export function useInvite(username, showMessage) {
	let sender = username;
	const [receiver, setRecv] = useState("");
	let invited = false;
	let invites;
	let [disabledButton, disable] = useState(false);
	const {setInvite} = useContext(inviteContext); 

	function setRecevier(r) {
		setRecv(r);
	}

	async function submitInvite() {
		if(sender != receiver) {
			disable(true);
			const inviteSent = await inviteRequest(sender, receiver);
			setTimeout(disable(false), 2000);

			inviteSent.sent ? invited = true : invited = false;
			await displayInvite();
			inviteSent.sent ? showMessage("Invite to " + receiver + " sent successfully!", "success") : showMessage("Invite to " + receiver + " failed to send. Ensure the username is spelled right and is correct", "error")
		}
		else if(sender == receiver) {
			invited = false;
			await displayInvite();
			showMessage("You cannot invite yourself to a game", "error")
		}
	}

	async function displayInvite() {
		invites = await getInvite(sender);
		setInvite(invites.senderList);
	}

	async function inviteOption(id, sender, accepted) {
		disable(true);
		await useInviteOption(id, username, sender, accepted);
		await displayInvite();
		disable(false);
		accepted ? showMessage("Invite Accepted!", "success") : showMessage("Invite Declined", "info")
	}

	async function haveNewInvites() {
		let size = invites ? invites.senderList.length : 0;

		await displayInvite();
		let newSize = invites ? invites.senderList.length : 0;

		newSize > size ? showMessage("You have new invites!", "info") : "";
	}

	return {invited, disabledButton, submitInvite, setRecevier, displayInvite, inviteOption, haveNewInvites}
}
