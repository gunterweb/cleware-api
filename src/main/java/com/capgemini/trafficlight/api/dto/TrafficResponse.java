package com.capgemini.trafficlight.api.dto;

import java.io.Serializable;

/**
 * Bean defining global response
 * 
 * @author fbontemp
 *
 */
public class TrafficResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5256769370251369854L;
    private String message;
    private String output;

    public TrafficResponse() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
