import React from 'react';
import { render, screen, act, fireEvent } from '@testing-library/react';
import { afterEach, beforeEach, describe, expect, it, jest } from '@jest/globals';
import SignIn from '../../../src/components/SignIn/SignIn';
import * as userAPI from '../../../src/utils/API/userAPI';
import { SnackbarProvider } from 'notistack';

describe('Tests for SignIn', () => {
    const testUserEmail = 'test@example.com';
    const testUserPassword = 'mypass';
    const testUserName = 'bob';

    let renderedComponent;
    let newUserSpy;
    let currentUserSpy;
    let hidden = jest.fn();
    let showMessage = jest.fn();

    const checkInputByLabel = (label, text) => {
        let input = screen.getByLabelText(label);

        act(() => {
            fireEvent.change(input, {target: {value: text }});
        });

        expect(input.value).toBe(text);
    }

    const awaitSumbission = async () => {
        return new Promise((resolve) => {
            fireEvent.click(screen.getByText('Submit'));
            resolve();
        });
    }

    beforeEach(() => {
        fetch.resetMocks();
        newUserSpy = jest.spyOn(userAPI, 'newUserRequest');
        currentUserSpy = jest.spyOn(userAPI, 'currentUserRequest');
        renderedComponent = render(<SnackbarProvider maxSnack={3} preventDuplicate><SignIn hide={hidden} showMessage={showMessage}/></SnackbarProvider>);
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    it('should render', () => {
        expect(renderedComponent).toBeTruthy();
    });

    it('should find all of the log in elements', () => {
        expect(screen.getByText('Log In')).toBeTruthy();
        expect(screen.getByLabelText('Email')).toBeTruthy();
        expect(screen.getByLabelText('Password')).toBeTruthy();
    });

    it('should change the page to Sign Up', () => {
        expect(screen.getByText('Log In')).toBeTruthy();
        expect(() => screen.getByText('Sign Up')).toThrow();


        act(() => {
            fireEvent.click(screen.getByText('Sign Up a New Account'),);
        });

        expect(() => screen.getByText('Log In')).toThrow();
        expect(screen.getByText('Sign Up')).toBeTruthy();
        expect(screen.getByLabelText('Email')).toBeTruthy();
        expect(screen.getByLabelText('Password')).toBeTruthy();
        expect(screen.getByLabelText('Confirm Password')).toBeTruthy();
        expect(screen.getByLabelText('New User Name')).toBeTruthy();


        act(() => {
            fireEvent.click(screen.getByText('Log In with Existing Account'),);
        });

        expect(screen.getByText('Log In')).toBeTruthy();
        expect(() => screen.getByText('Sign Up')).toThrow();
    });

    it('should be able to use inputs', () => {
        expect(screen.getAllByPlaceholderText(/.*/)).toHaveLength(2);
        checkInputByLabel('Email', testUserEmail);
        checkInputByLabel('Password', testUserPassword);


        act(() => {
            fireEvent.click(screen.getByText('Sign Up a New Account'),);
        });

        expect(screen.getAllByPlaceholderText(/.*/)).toHaveLength(4);
        checkInputByLabel('Confirm Password', testUserPassword);
        checkInputByLabel('New User Name', testUserName);
    });

    it('should submit the form for a current user', async () => {
        // TODO add actual test verification when this does something tangible
        currentUserSpy.mockImplementation();
        act(()=>{
            fireEvent.click(screen.getByText("Log In"),);
        });
        checkInputByLabel('Email', testUserEmail);
        checkInputByLabel('Password', testUserPassword);


        await act(awaitSumbission);
        expect(currentUserSpy).toHaveBeenCalledTimes(1);
        expect(currentUserSpy).toHaveBeenCalledWith(testUserEmail, testUserPassword);
    });

    it('should submit the form for a new user', async () => {
        newUserSpy.mockImplementation();
        
        act(() => {
            fireEvent.click(screen.getByText('Sign Up a New Account'),);
        });

        checkInputByLabel('Email', testUserEmail);
        checkInputByLabel('Password', testUserPassword);
        checkInputByLabel('Confirm Password', testUserPassword);
        checkInputByLabel('New User Name', testUserName);


        await act(awaitSumbission);

        expect(newUserSpy).toHaveBeenCalledTimes(1);
        expect(newUserSpy).toHaveBeenCalledWith(testUserEmail, testUserPassword, testUserName);
    });

    it('should fail to submit a new user due to password incosistancy', async () => {
        newUserSpy.mockImplementation();

        act(() => {
            fireEvent.click(screen.getByText('Sign Up a New Account'),);
        });

        checkInputByLabel('Email', testUserEmail);
        checkInputByLabel('Password', testUserPassword);
        checkInputByLabel('Confirm Password', testUserPassword + '1');
        checkInputByLabel('New User Name', testUserName);

        await act(awaitSumbission);

        expect(newUserSpy).not.toHaveBeenCalled();
    });
});
