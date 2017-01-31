package com.capgemini.trafficlight;

import com.capgemini.trafficlight.exception.TrafficLightException;

/**
 * Sensor interface
 * 
 * @author fbontemp
 *
 */
public interface Sensor {
    /**
     * Display Help for Sensor
     * 
     * @return String containing output
     * @throws TrafficLightException
     *             TrafficLightException
     */
    public String displayHelp() throws TrafficLightException;

    /**
     * Turns on Sensor for a Color
     * 
     * @param color
     *            color
     * @return String containing output
     * @throws TrafficLightException
     *             TrafficLightException
     */
    public String turnOn(Color color) throws TrafficLightException;

    /**
     * Turns off Sensor for a Color
     * 
     * @param color
     *            color
     * @return String containing output
     * @throws TrafficLightException
     *             TrafficLightException
     */
    public String turnOff(Color color) throws TrafficLightException;
}
