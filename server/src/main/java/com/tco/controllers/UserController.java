package com.tco.controllers;

import com.tco.models.UserModel;
import com.tco.requests.Request;
import com.tco.requests.user.CurrentUsersRequest;
import com.tco.requests.user.NewUserRequest;
import com.tco.requests.user.GetUserStatsRequest;
import com.tco.misc.BadRequestException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserController extends AbstractController {
    private UserModel model;

    public UserController() {
        model = new UserModel();
    }

    // Test constructor since Mockito cannot mock constructors
    public UserController(UserModel testModel) {
        model = testModel;
    }

    public Request newUserHandler(Request newUserRequest) throws BadRequestException {
        NewUserRequest newUserResponse = (NewUserRequest)newUserRequest;
        
        model.createNewUser(newUserResponse.getEmail(), newUserResponse.getUserName(), newUserResponse.getPassword());

        newUserResponse.prepareResponse();

        return newUserResponse;
    }

    public Request currentUserHandler(Request currentUsersRequest) throws BadRequestException{
        CurrentUsersRequest currentRequest = (CurrentUsersRequest)currentUsersRequest;

        if(currentRequest.getUnregister()) {
            Boolean unregistered = model.removeCurrentUser(currentRequest.getEmail(), currentRequest.getUserName(), currentRequest.getPassword());
            currentRequest.setUnregister(unregistered);
        }
        else {
            String existingUserName = model.createCurrentUsers(currentRequest.getEmail(), currentRequest.getUserName(), currentRequest.getPassword());
            if (existingUserName != null) {
                currentRequest.setUserName(existingUserName);
            } else {
                throw new BadRequestException();
            }
        }

        currentRequest.prepareResponse();
        return currentRequest;
    }


    public void updateStatsAfterGame(String p1Name, String p2Name, char winner) {
        this.model.updateUserStats(p1Name, winner == 'w');
        this.model.updateUserStats(p2Name, winner == 'b');
    }

    public Request getStatsHandler(Request getUserStatsRequest) throws BadRequestException{
        GetUserStatsRequest statsRequest = (GetUserStatsRequest)getUserStatsRequest;

        ArrayList<HashMap<String, String>> result= model.getUserStats(statsRequest.getUserName());

        statsRequest.prepareResponse();

        statsRequest.setWins(Integer.parseInt(result.get(0).get("Wins")));
        statsRequest.setLosses(Integer.parseInt(result.get(0).get("Losses")));
        statsRequest.setGamesPlayed(Integer.parseInt(result.get(0).get("Games_Played")));


        return statsRequest;
    }


}
