
package com.tco.requests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



public class TestDatabase {
    private ResultSetMetaData mockResultSetMetaData = mock(ResultSetMetaData.class);

    private ResultSet mockResultSet = mock(ResultSet.class);

    private Statement mockStatement = mock(Statement.class);

    private Connection mockConnection = mock(Connection.class);
   
    @BeforeEach
    public void beforeEach() {
        try {
            when(mockResultSetMetaData.getColumnLabel(anyInt())).thenReturn("Email", "Username", "User_ID", "Password");
            when(mockResultSetMetaData.getColumnCount()).thenReturn(4);
            when(mockResultSet.getMetaData()).thenReturn(mockResultSetMetaData);
            when(mockResultSet.getString(anyInt())).thenReturn("anthony@gmail.com", "JohnDoe3", "2", "john20");
            when(mockResultSet.next()).thenReturn(true, false);
            when(mockStatement.executeQuery(any())).thenReturn(mockResultSet);
            when(mockConnection.createStatement()).thenReturn(mockStatement);
        } catch(Exception e) {
            System.err.println("TEST ERROR: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Query")
    public void testQueryDB() {
        try (MockedStatic<DriverManager> mockDriverManager = mockStatic(DriverManager.class)) {    
            mockDriverManager.when(() -> 
                DriverManager.getConnection(anyString(), anyString(), anyString())
            ).thenReturn(mockConnection);
            
            ArrayList<HashMap<String,String>> data = null;
            String query = "SELECT * FROM User_Information";
            data = Database.queryDB(query);

            assertEquals(data.get(0).get("Email"),"anthony@gmail.com");
            assertEquals(data.get(0).get("Username"),"JohnDoe3");
            assertEquals(data.get(0).get("User_ID"),"2");
            assertEquals(data.get(0).get("Password"),"john20");
        
        } catch(Exception e) {
            System.err.println("TEST ERROR: " + e.getMessage());
        }
    }
}
 

