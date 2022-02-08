import React from 'react';
import { render, screen, fireEvent, act } from '@testing-library/react';
import user from '@testing-library/user-event';
import * as mockGameApi from '../../src/utils/API/gameAPI';
import { beforeEach, describe, expect, it, jest } from '@jest/globals';
import { VALID_CONFIG_RESPONSE } from '../sharedMocks';
import { SnackbarProvider} from 'notistack';

import Page from '../../src/components/Page';
import { gameDisplayContext } from '../../src/utils/contexts/gameDisplay';

describe('Page', () => {
    let renderedComponent;

    // async calls in useServerSettings do not resolve without awaiting
    async function renderPage() {
        return new Promise((resolve) => {
            renderedComponent = render(<gameDisplayContext.Provider value={{games: "", setGame: jest.fn()}}><SnackbarProvider maxSnack={3} preventDuplicate><Page /></SnackbarProvider ></gameDisplayContext.Provider>);
            resolve();
        });
    }

    beforeEach(async () => {
        jest.spyOn(mockGameApi, 'getInitialState').mockResolvedValue({ features: [], type: '', board: { a1: 'wQ' }});
        jest.spyOn(mockGameApi, 'getGame').mockResolvedValue({ features: [], setDisplay:[]});
        fetch.resetMocks();
        fetch.mockResponse(VALID_CONFIG_RESPONSE);

        await act(renderPage);
    });

    it('should render', () => {
        expect(renderedComponent).toBeTruthy();
    });

    it('should navigate to the correct tab', () => {
        const tab1 = screen.getByText('Sign In');
        // const tab2 = screen.getByText('My Profile');
        // const tab3 = screen.getByText("Invitations");

        expect(tab1.classList.values()).toContain('active');
        // expect(tab2.classList.values()).not.toContain('active');
        // expect(tab3.classList.values()).not.toContain("active");


        // act(() => {
        //     fireEvent.click(tab2);
        // });

        // expect(tab1.classList.values()).not.toContain('active');
        // expect(tab2.classList.values()).toContain('active');
        // expect(tab3.classList.values()).not.toContain("active");

        // act(() => {
        //     fireEvent.click(tab3);
        // });

        // expect(tab1.classList.values()).not.toContain('active');
        // expect(tab2.classList.values()).not.toContain('active');
        // expect(tab3.classList.values()).toContain("active");

        // act(() => {
        //     fireEvent.click(tab1);
        // });

        // expect(tab1.classList.values()).toContain('active');
        // expect(tab2.classList.values()).not.toContain('active');
        // expect(tab3.classList.values()).not.toContain("active");
        
        // act(() => {
        //     fireEvent.click(tab1);
        // });

        // expect(tab1.classList.values()).toContain('active');
        // expect(tab2.classList.values()).not.toContain('active');
        // expect(tab3.classList.values()).not.toContain("active");
    });
});
