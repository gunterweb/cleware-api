package com.capgemini.trafficlight;

import com.capgemini.trafficlight.exception.FunctionalException;
import com.capgemini.trafficlight.exception.FunctionalReason;

/**
 * Enumeration for traffic light colors
 * 
 * @author fbontemp
 *
 */
public enum Color {
    RED("0", "Red"), YELLOW("1", "Yellow"), GREEN("2", "Green");

    private final String code;
    private final String label;

    private Color(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Gets color elements from code
     * 
     * @param code
     *            code
     * @return Color
     * @throws FunctionalException
     *             FunctionalException
     */
    public static Color getColorFromCode(String code) throws FunctionalException {
        if ("0".equalsIgnoreCase(code)) {
            return RED;
        } else if ("1".equalsIgnoreCase(code)) {
            return YELLOW;
        } else if ("2".equalsIgnoreCase(code)) {
            return GREEN;
        } else {
            throw new FunctionalException(FunctionalReason.COLOR_NOT_FOUND);
        }
    }

}
