import { getOriginalServerUrl, sendAPIRequest } from "./restfulAPI";

const inviteBaseURL = "/sendInvite";

export async function inviteRequest(sender, receiver) {
	const body = {requestType: "sendInvite", sender: sender, receiver: receiver}
	const url = getOriginalServerUrl();

	return sendAPIRequest(body, url);
}
