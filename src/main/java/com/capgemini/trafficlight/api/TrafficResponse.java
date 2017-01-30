package com.capgemini.trafficlight.api;

import java.io.Serializable;

public class TrafficResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5256769370251369854L;
    private String message;
    private String errorMessage;

    public TrafficResponse() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
