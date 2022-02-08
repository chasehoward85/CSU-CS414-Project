package com.tco.requests;

import com.tco.misc.BadRequestException;

public abstract class Request {

    protected String requestType;

    public String getRequestType() {
        return requestType;
    }

    // Overrideable Methods
    public abstract void prepareResponse() throws BadRequestException;
}