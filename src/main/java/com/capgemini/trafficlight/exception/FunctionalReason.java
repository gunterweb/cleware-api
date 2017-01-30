package com.capgemini.trafficlight.exception;

/**
 * Enum for functional reasons
 * @author fbontemp
 *
 */
public enum FunctionalReason {

    DEFAULT_ERROR("default.error"),    
    COLOR_NOT_FOUND("color.notFound")
    ;

    private final String key;

    private FunctionalReason(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
