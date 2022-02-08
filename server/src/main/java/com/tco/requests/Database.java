package com.tco.requests;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Database {
    
    private final static String DB_USER = "axiang";
    private final static String DB_PASSWORD = "831597110";
    private static String DB_URL;
    
    private static String setUrl() {
        String useTunnel = System.getenv("cs414_team7");

        if(useTunnel != null && useTunnel.equals("true")) {
            return "jdbc:mariadb://127.0.0.1:56013/cs414_team7";
        }
        else {
            return "jdbc:mariadb://faure.cs.colostate.edu/cs414_team7";
        }   
    }

    private static String escapeSql(String unescaped) {
        return unescaped.replaceAll("([-.$@\']{1})", "\\\\$1");
    }

    public static ArrayList<HashMap<String, String>> queryDB(String query) {
        DB_URL = setUrl();
        
        try (
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(escapeSql(query));
        ) {
            return process(result);
        } catch(SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }

        return null;
    }

    private static ArrayList<HashMap<String, String>> process(ResultSet result) throws SQLException{
        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();

        ResultSetMetaData meta = result.getMetaData();
        int columns = meta.getColumnCount();
        int index = 0;
        while(result.next()){
            data.add(new HashMap<String,String>());
            for(int i=1; i <= columns; i++){
                String key = meta.getColumnLabel(i);
                String value = result.getString(i);
                data.get(index).put(key,value);
            }
            index++;
        }
        return data;
    }
}