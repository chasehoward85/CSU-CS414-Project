import { getOriginalServerUrl, sendAPIRequest } from "./restfulAPI";

const inviteBaseURL = "/getInvite";

export async function getInvite(user) {
	const body = {requestType: "getInvite", userName:user}
	const url = getOriginalServerUrl();

	return sendAPIRequest(body, url);
}