package com.tco.requests.user;

import com.tco.requests.Request;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewUserRequest extends Request {

    private String email;
    private String password;
    private String userName;
    private final transient Logger log = LoggerFactory.getLogger(NewUserRequest.class);
    private ArrayList<String> features;

    @Override
    public void prepareResponse() {
        this.email = null;
        this.password = null;
        this.features = new ArrayList<>();
        features.add("newUser");
        log.trace("buildResponse -> {}", this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public NewUserRequest() {
        this.requestType = "newUser";
    }

    public NewUserRequest(String email, String password, String userName) {
        this();
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public boolean validFeature(String feature){
        return features.contains(feature);
    }
}