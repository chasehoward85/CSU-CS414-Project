import { getOriginalServerUrl, sendAPIRequest } from "./restfulAPI";

const userBaseUrl = '/user';

export async function newUserRequest(email, password, newUserName) {
    const body = { requestType: 'newUser', email: email, password: password, userName: newUserName };
    const url = getOriginalServerUrl();

    return sendAPIRequest(body, url);
}

export async function unregisterRequest(email, password, newUserName, unregister) {
    const body = { requestType: 'currentUser', email: email, password: password, userName: newUserName, unregister: unregister };
    const url = getOriginalServerUrl();

    return sendAPIRequest(body, url);
}

export async function currentUserRequest(email, password ) {
    const body = { requestType: 'currentUser', email: email, password: password};
    const url = getOriginalServerUrl();

    return sendAPIRequest(body, url);
}

export async function userStatsRequest(userName) {
    const body = { requestType: 'getStats', userName: userName};
    const url = getOriginalServerUrl();

    return sendAPIRequest(body, url);
}