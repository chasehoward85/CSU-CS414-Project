import { useState, useContext } from 'react';
import { newUserRequest, currentUserRequest, unregisterRequest } from '../utils/API/userAPI';
import { userData } from '../components/About/profileInfo';
import { useToggle } from './useToggle';
import { usernameContext } from '../utils/contexts/username';

export function useSignIn(showMessage) {
    const [logIn, toggleLogIn] = useToggle(true);
    const [validUserEmail, setValidUserEmail] = useState(true);
    const [validUserPassword, setValidUserPassword] = useState(false);
    const [userEmail, setUserEmail] = useState('');
    const [newUserName, setNewUserName] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [confirmed, setConfirmed] = useState('');
    let user;
    const { username, setUsername } = useContext(usernameContext);


    function validateUserEmail(newUserEmail) {
        //TODO add validation
        setValidUserEmail(true);
        setUserEmail(newUserEmail)
    }

    function validateUserPassword(newUserPassword) {

        if(newUserPassword.length<=8){
            setValidUserPassword(true);
        }
        setUserPassword(newUserPassword);
    
    }

    function confirmUserPassword(confirmedUserPassword) {
        //TODO add validation that notifies user if they don't match
        setConfirmed(confirmedUserPassword);
    }

    async function submit() {
        if (logIn) {
            user = await currentUserRequest(userEmail, userPassword);
        } else if (validUserEmail && validUserPassword && userPassword === confirmed) {
            user = await newUserRequest(userEmail, userPassword, newUserName);
        }

        if(user) {
            userData.setEmail(userEmail);
            setUsername(user.userName);
        } else {
            showMessage("Please enter valid credentials!", "error");
        }
    }

    async function unregisterUser() {
        let unregistered = await unregisterRequest(userEmail, userPassword, username, true)
        if(unregistered.unregister) {
            setUsername("");
            showMessage("Successfully unregistered", "success");
            setTimeout(window.location.reload(), 3000);
        }
        else {
            showMessage("An error occurred while attempting to unregister. Please try again", "error");
        }
    }

    return { logIn, toggleLogIn, validUserEmail, validateUserEmail, validateUserPassword, confirmUserPassword, setNewUserName, submit, unregisterUser }
}
