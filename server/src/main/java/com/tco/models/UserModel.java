package com.tco.models;

import com.tco.requests.user.NewUserRequest;
import com.tco.requests.user.CurrentUsersRequest;
import com.tco.requests.user.GetUserStatsRequest;

import com.tco.requests.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class UserModel extends AbstractModel {
 
   public UserModel() {
        super();
    }

    public String createNewUser(String userEmail, String userName, String userPassword) {
        String stmntString = String.format("INSERT into User_Information (Email, Username, Password)\n"
         + "VALUES (\"%s\", \"%s\", \"%s\");", userEmail, userName, userPassword);

        String statsInsert = "INSERT INTO User_Stats (Username) VALUES (\"" + userName + "\");";
 
        Database.queryDB(statsInsert);
        Database.queryDB(stmntString);

        String getString = String.format("SELECT Username FROM User_Information WHERE Email = \"%s\"", userEmail);
        ArrayList<HashMap<String, String>> result = Database.queryDB(getString);

        return result.get(0).get("Username");
    }

    public String createCurrentUsers(String email, String userName, String password){
        String statsR =String.format("Select Username From User_Information where Email = \"%s\" AND password = \"%s\"", email, password);
        ArrayList<HashMap<String, String>> result = Database.queryDB(statsR);
        return result.get(0).get("Username");
    }

    public ArrayList<HashMap<String, String>> getUserStats(String userName){
        String getInfo =String.format("select Wins, Losses, Games_Played from User_Stats where Username = \"%s\";", userName);
        ArrayList<HashMap<String, String>> result = Database.queryDB(getInfo);
        return result;
    }

    public Boolean removeCurrentUser(String email, String username, String password) {
        String checkInformation = "SELECT * FROM User_Information WHERE Email = \"" + email + "\" AND Username=\"" + username +
        "\" AND Password=\"" + password + "\";";

        String removeUserInformation = "DELETE FROM User_Information WHERE Email=\"" + email + "\" AND Password=\"" + password + 
        "\" AND Username=\"" + username + "\";";
        
        String removeStats = "DELETE FROM User_Stats WHERE Username=\"" + username + "\";";

        String updateTurn = "UPDATE Game_Information SET Turn=3 WHERE P1=\"" + username + "\" OR P2=\"" + username + "\";";

        String updateInvite = "UPDATE Game_Invitation SET Accepted=0 WHERE Sender=\"" + username + "\" OR Receiver=\"" + username + "\";";

        try {
            ArrayList<HashMap<String, String>> user = Database.queryDB(checkInformation);
            
            if(user.get(0).size() == 0) {
                throw new Exception();
            }

            Database.queryDB(removeUserInformation);
            Database.queryDB(removeStats);
            Database.queryDB(updateTurn);
            Database.queryDB(updateInvite);

            return true;
        } catch(Exception e) {
            return false;
        }
    }


    public void updateUserStats(String username, boolean winner) {
        String getStats = String.format(
            "SELECT * " +
            "FROM User_Stats " +
            "WHERE Username=\"%s\"",
            username
        );

        System.out.println(getStats);

        HashMap<String, String> result = Database.queryDB(getStats).get(0);

		String updateStats = String.format(
            "UPDATE User_Stats " +
            "SET Wins=%d, Losses=%d, Games_Played=%d " +
            "WHERE Username=\"%s\";",
            Integer.parseInt(result.get("Wins")) + (winner ? 1 : 0),
            Integer.parseInt(result.get("Losses")) + (winner ? 0 : 1),
            Integer.parseInt(result.get("Games_Played")) + 1,
            username
        );

        Database.queryDB(updateStats);
    }

}
