import React from 'react';
import { render, screen } from '@testing-library/react';
import { beforeEach, describe, it } from '@jest/globals';
import { LOG } from '../../src/utils/constants';
import App from '../../src/components/App';
import * as mockGameApi from '../../src/utils/API/gameAPI';

describe('App', () => {
    beforeEach(() => {
        fetch.resetMocks();
        jest.spyOn(mockGameApi, 'getInitialState').mockResolvedValue({ features: [], type: '', board: { a1: 'wQ' }});
        jest.spyOn(mockGameApi, 'getGame').mockResolvedValue({ features: [], setDisplay:[]});
    });

    it('shows error snackbar if no server config', async () => {
        jest.spyOn(LOG, 'error').mockImplementation(() => {});
        fetch.mockReject(() => Promise.reject("API is down (expected)."));

        render(<App />);

        await screen.findByText(/failed/i);
    });
});
