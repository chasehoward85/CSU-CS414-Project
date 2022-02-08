package com.tco.models;

import com.tco.requests.invite.SendInviteRequest;
import com.tco.requests.invite.GetInviteRequest;
import com.tco.requests.Database;

import java.util.ArrayList;
import java.util.HashMap;

public class InviteModel {
	private String sender;
	private String receiver;
	private boolean sent;

	public InviteModel() {
		super();
	}

	public boolean createInvite(SendInviteRequest invite) {
		String findReceiver = "SELECT Username FROM User_Information WHERE Username=\"" + invite.getReceiver() + "\";";
		String insertInvite = "INSERT INTO Game_Invitation (Sender, Receiver) VALUES (\"" + invite.getSender() + "\", \"" + invite.getReceiver() + "\");";
		String testInvite = "SELECT * FROM Game_Invitation WHERE Sender=\"" + invite.getSender() + "\" AND Receiver=\"" + invite.getReceiver() + "\";";
		
		try {
			ArrayList<HashMap<String, String>> testReceiver = Database.queryDB(findReceiver);

			if(testReceiver.get(0).size() == 0) {
				throw new Exception();
			}

			Database.queryDB(insertInvite);
			ArrayList<HashMap<String, String>> result = Database.queryDB(testInvite);

			if(result.get(0).size() == 0) {
				throw new Exception();
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public ArrayList<HashMap<String, String>> getInvite(String userName){
		String getInviteSQL = "SELECT Invite_ID,Sender FROM Game_Invitation WHERE Receiver = \""+ userName + "\" AND Accepted = 2;";
		ArrayList<String> userSender = new ArrayList<String>();
		ArrayList<HashMap<String, String>> result = Database.queryDB(getInviteSQL);
		
		return result;
	}

	
}
