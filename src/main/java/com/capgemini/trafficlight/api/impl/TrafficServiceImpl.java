package com.capgemini.trafficlight.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.trafficlight.Color;
import com.capgemini.trafficlight.Sensor;
import com.capgemini.trafficlight.api.TrafficService;
import com.capgemini.trafficlight.api.dto.TrafficResponse;
import com.capgemini.trafficlight.exception.FunctionalException;
import com.capgemini.trafficlight.exception.TrafficLightException;

@Service("TrafficService")
public class TrafficServiceImpl implements TrafficService {

    @Autowired
    Sensor sensor;
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficServiceImpl.class);

    @Override
    public TrafficResponse home() throws TrafficLightException {
        String output;
        try {
            output = sensor.displayHelp();
        } catch (TrafficLightException e) {
            LOGGER.error("Error in displayHelp", e);
            throw e;
        }
        TrafficResponse response = new TrafficResponse();
        response.setMessage("displayHelp invoked");
        response.setOutput(output);
        return response;
    }

    @Override
    public TrafficResponse turnOn(String color) throws TrafficLightException {
        Color col;
        String output;
        try {
            col = checkColor(color);
            output = sensor.turnOn(col);
        } catch (TrafficLightException e) {
            LOGGER.error("Error in turnOn", e);
            throw e;
        }
        TrafficResponse response = new TrafficResponse();
        response.setMessage("turnOn Color " + col.getLabel() + " invoked");
        response.setOutput(output);
        return response;
    }

    @Override
    public TrafficResponse turnOff(String color) throws TrafficLightException {
        Color col;
        String output;
        try {
            col = checkColor(color);
            output = sensor.turnOff(col);
        } catch (TrafficLightException e) {
            LOGGER.error("Error in turnOn", e);
            throw e;
        }
        TrafficResponse response = new TrafficResponse();
        response.setMessage("turnOff Color " + col.getLabel() + " invoked");
        response.setOutput(output);
        return response;
    }

    private Color checkColor(String color) throws FunctionalException {
        return Color.getColorFromCode(color);
    }

}
