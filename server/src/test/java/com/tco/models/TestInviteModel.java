package com.tco.models;

import com.tco.requests.invite.GetInviteRequest;

import com.tco.requests.Database;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.MockedStatic;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInviteModel {
    InviteModel model;
    @BeforeEach
    public void beforeEach() {
        model = new InviteModel();
    }

    @Test
    @DisplayName("InviteModel contructs")
    public void testInviteModelConstructor() {
        assertNotNull(model);
    }

    // @Test
    // @DisplayName("test getInvite")
    // public void testInviteModelNewUser() {
    //     ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
    //     data.add(new HashMap<String, String>());
    //     data.get(0).put("Sender", "test2");
    //     data.get(0).put("Receiver", "test");
    //     data.get(0).put("Accepted", "2");

    //     try (MockedStatic<Database> mockDatabase = mockStatic(Database.class)) {    
    //         mockDatabase.when(() -> 
    //             Database.queryDB(anyString())
    //         ).thenReturn(data);
    //         ArrayList<String> result = model.getInvite("test");
    //         ArrayList<String> storeUser = new ArrayList<String>();
    //         storeUser.add("test2");
    //         assertEquals(result, storeUser);

    //     } catch(Exception e) {
    //         assertEquals("Test should not throw: ", e);
    //     }
    // }


    
}
