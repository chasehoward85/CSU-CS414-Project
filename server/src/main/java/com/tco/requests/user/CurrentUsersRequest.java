package com.tco.requests.user;
import com.tco.requests.Request;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentUsersRequest extends Request {
    private String email;
    private String password;
    private String userName;
    private Boolean unregister = false;
    private final transient Logger log = LoggerFactory.getLogger(CurrentUsersRequest.class);
    private ArrayList<String> features;

    @Override
    public void prepareResponse() {
        this.email = null;
        this.password = null;
        this.unregister = this.unregister ? true : null;
        this.features = new ArrayList<>();
        features.add("currentUser");
        log.trace("buildResponse -> {}", this);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setUnregister(Boolean unregistered) {
        this.unregister = unregistered;
    }

    public Boolean getUnregister() {
        return this.unregister;
    }


  /* The following methods exist only for testing purposes and are not used
  during normal execution, including the constructor. */

    public CurrentUsersRequest() {
        this.requestType = "currentUser";
    }

    public CurrentUsersRequest(String email, String userName, String password) {
        this();
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public CurrentUsersRequest(String email, String userName, String password, Boolean unregister) {
        this();
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.unregister = unregister;
    }

    public boolean validFeature(String feature){
        return features.contains(feature);
    }

    



  

}
