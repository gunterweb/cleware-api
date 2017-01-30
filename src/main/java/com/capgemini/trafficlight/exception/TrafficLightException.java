package com.capgemini.trafficlight.exception;

public class TrafficLightException extends Exception {
    private final boolean functional;
    /**
     * 
     */
    private static final long serialVersionUID = 64772415297374564L;

    public TrafficLightException(String message, Throwable cause) {
        super(message, cause);
        this.functional = checkFunctionalException(cause);
    }

    public TrafficLightException(String message) {
        super(message);
        this.functional = checkFunctionalException();
    }

    public TrafficLightException() {
        super();
        this.functional = checkFunctionalException();
    }

    public TrafficLightException(Throwable cause) {
        super(cause);
        this.functional = checkFunctionalException(cause);
    }

    public boolean isFunctional() {
        return functional;
    }

    public boolean isTechnical() {
        return !functional;
    }

    private boolean checkFunctionalException() {
        return this instanceof FunctionalException;

    }

    private boolean checkFunctionalException(Throwable cause) {
        return cause instanceof FunctionalException;
    }
    
    /**
     * 
     * @return internationalized message
     */
    public String getInternationalizedMessage() {
        return this.getMessage();
    }
}
