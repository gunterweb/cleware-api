package com.capgemini.trafficlight.exception;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Main functional exception
 * 
 * @author fbontemp
 *
 */
public class FunctionalException extends TrafficLightException {

    private static final String MESSAGE_NOT_DEFINED_FOR_REASON = "Message not defined for reason : ";
    private static final String RESOURCE_BUNDLE_NOT_FOUND = "ResourceBundle not found";
    private static final String REASON_NOT_SET_ON_ERROR = "Reason not set on error";
    
    private static final String ERROR_BUNDLE = "errorMessages";
    /** Logger */
    private static final Logger LOG = Logger.getLogger(FunctionalException.class);
    /**
     * 
     */
    private static final long serialVersionUID = -4120985416048966288L;
    
    private final FunctionalReason functionalReason;

    /**
     * @param functionalReason
     *            the reason
     */
    public FunctionalException(FunctionalReason functionalReason) {
        super();
        this.functionalReason = functionalReason;
    }

    /**
     * @param functionalReason
     *            the reason
     * @param e
     *            Exception
     */
    public FunctionalException(FunctionalReason functionalReason, Exception e) {
        super(e);
        this.functionalReason = functionalReason;
    }

    /**
     * 
     * @return internationalized message
     */
    @Override
    public String getInternationalizedMessage() {
        DefaultFunctionalException def = new DefaultFunctionalException();
        String message = def.getInternationalizedMessage();
        Locale currentLocale = Locale.getDefault();
        ResourceBundle errorBundle = ResourceBundle.getBundle(ERROR_BUNDLE, currentLocale);
        if (errorBundle != null) {
            if (functionalReason != null) {
                String errorMessage = null;
                try {
                    errorMessage = errorBundle.getString(functionalReason.getKey());
                } catch (MissingResourceException e) {
                    LOG.info(MESSAGE_NOT_DEFINED_FOR_REASON + functionalReason.getKey(), e);
                }
                if (errorMessage != null && !"".equals(errorMessage)) {
                    message = errorMessage;
                }
            } else {
                LOG.warn(REASON_NOT_SET_ON_ERROR);
            }
        } else {
            LOG.warn(RESOURCE_BUNDLE_NOT_FOUND);
        }
        return message;
    }

    @Override
    public String getLocalizedMessage() {
        return getInternationalizedMessage();
    }

    /**
     * Default Functional Exception
     * 
     * @author fbontemp
     *
     */
    private static class DefaultFunctionalException extends FunctionalException {
        private static final String FUNCTIONAL_ERROR = "Functional error";
        /**
         * 
         */
        private static final long serialVersionUID = 5966590558573317218L;

        private DefaultFunctionalException() {
            super(FunctionalReason.DEFAULT_ERROR);
        }

        @Override
        public String getInternationalizedMessage() {
            return FUNCTIONAL_ERROR;
        }
    }
}
