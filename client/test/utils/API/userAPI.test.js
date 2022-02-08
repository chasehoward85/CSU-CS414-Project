import { newUserRequest, userStatsRequest } from "../../../src/utils/API/userAPI";
import * as restAPI from '../../../src/utils/API/restfulAPI';
import { afterAll, afterEach, beforeEach, expect, jest } from "@jest/globals";

describe('Tests for userAPI', () => {
    let requestSpy;
    let urlSpy;

    beforeEach(() => {
        requestSpy = jest.spyOn(restAPI, 'sendAPIRequest');
        urlSpy = jest.spyOn(restAPI, 'getOriginalServerUrl');
    });

    afterEach(() => {
        jest.clearAllMocks();
    });

    afterAll(() => {
        process.env = ACTUAL_ENV;
    });

    it('should sucessfully create a new user', async () => {
        const testEmail = 'test@example.com';
        const testPassword = 'mysecretpassword';
        const testNewUserName = 'bob';
        const expectedRequest = {
            requestType: 'newUser',
            email: testEmail,
            password: testPassword,
            userName: testNewUserName
        };

        requestSpy.mockResolvedValue('test');
        urlSpy.mockReturnValue('http://localhost:9001');

        const actual = await newUserRequest(testEmail, testPassword, testNewUserName);

        expect(urlSpy).toHaveBeenCalledTimes(1);
        expect(requestSpy).toHaveBeenCalledTimes(1);
        expect(requestSpy).toHaveBeenCalledWith(expectedRequest, 'http://localhost:9001');
        expect(actual).toBe('test');
    });
  
    it('should sucessfully get player statistics', async () => {
        const testNewUserName = 'bob';
        const expectedRequest = {
            requestType: 'getStats',
            userName: testNewUserName
        };
  
        requestSpy.mockResolvedValue('test');
        urlSpy.mockReturnValue('http://localhost:9001');
  
        const actual = await userStatsRequest(testNewUserName);
 
        expect(urlSpy).toHaveBeenCalledTimes(1);
        expect(requestSpy).toHaveBeenCalledTimes(1);
        expect(requestSpy).toHaveBeenCalledWith(expectedRequest, 'http://localhost:9001');
        expect(actual).toBe('test');
    });
});
