import { getOriginalServerUrl, sendAPIRequest } from "./restfulAPI";

const inviteBaseURL = "/getInitialState";

export async function useInviteOption(id, receiver, sender, accepted) {
	const body = {requestType: "getInitialState", id: parseInt(id), p1: receiver, p2: sender, accepted: accepted}
	const url = getOriginalServerUrl();

	return sendAPIRequest(body, url);
}
