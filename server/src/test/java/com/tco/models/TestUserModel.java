package com.tco.models;

import com.tco.requests.user.NewUserRequest;
import com.tco.requests.user.CurrentUsersRequest;

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

public class TestUserModel {
    UserModel model;

    @BeforeEach
    public void beforeEach() {
        model = new UserModel();
    }

    @Test
    @DisplayName("UserModel contructs")
    public void testUserModelConstructor() {
        assertNotNull(model);
    }

    @Test
    @DisplayName("UserModel creates a new user record")
    public void testUserModelNewUser() {
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        data.add(new HashMap<String, String>());
        data.get(0).put("Email", "bob@test.example");
        data.get(0).put("Password", "mySecretPassword");
        data.get(0).put("Username", "bob");

        try (MockedStatic<Database> mockDatabase = mockStatic(Database.class)) {    
            mockDatabase.when(() -> 
                Database.queryDB(anyString())
            ).thenReturn(data);

            String result = model.createNewUser("bob@test.example", "mySecretPassword", "bob");

            assertEquals(result, data.get(0).get("Username"));

        } catch(Exception e) {
            assertEquals("Test should not throw: ", e);
        }
    }
    
        @Test
        @DisplayName("UserModel checks if username, email, and password match")
        public void testUserModelCurrentUser() {
            ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
            data.add(new HashMap<String, String>());
            data.get(0).put("Email", "bob@test.example");
            data.get(0).put("Password", "mySecretPassword");
            data.get(0).put("Username", "bob");
    
            try (MockedStatic<Database> mockDatabase = mockStatic(Database.class)) {    
                mockDatabase.when(() -> 
                    Database.queryDB(anyString())
                ).thenReturn(data);
                
                CurrentUsersRequest currentRequest = new CurrentUsersRequest("bob@test.example", "bob", "mySecretPassword");
                String result = model.createCurrentUsers(data.get(0).get("Email"), data.get(0).get("UserName"), data.get(0).get("Password"));
                assertEquals(result, data.get(0).get("Username"));
    
            } catch(Exception e) {
                assertEquals("Test should not throw: ", e);
            }
        }

    }


