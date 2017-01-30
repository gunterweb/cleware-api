package com.capgemini.trafficlight;

import com.capgemini.trafficlight.exception.TrafficLightException;

/**
 * Sensor interface
 * 
 * @author fbontemp
 *
 */
public interface Sensor {
    public void displayHelp() throws TrafficLightException;

    public void turnOn(Color color) throws TrafficLightException;

    public void turnOff(Color color) throws TrafficLightException;
}
