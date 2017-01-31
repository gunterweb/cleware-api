package com.capgemini.trafficlight.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.capgemini.trafficlight.Color;
import com.capgemini.trafficlight.Sensor;
import com.capgemini.trafficlight.exception.TrafficLightException;

/**
 * Implementation of Sensor for CLeware Traffic light
 * 
 * @author fbontemp
 *
 */
@Component
public class ClewareSensor implements Sensor {

    private static final String TURN_ON_COMMAND = "1";
    private static final String TURN_OFF_COMMAND = "0";
    private static final String HELP_COMMAND = "-h";

    @Value("${log.path}")
    private File logFile;
    @Value("${command.path}")
    private String commandPath;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareSensor.class);

    /**
     * Process execution
     * 
     * @param args
     *            args
     * @return String output
     * @throws TrafficLightException
     *             TrafficLightException
     */
    private String executeProcess(String... args) throws TrafficLightException {
        ProcessBuilder pb = new ProcessBuilder(args);
        StringBuilder sb = new StringBuilder("Execute command : ");
        sb.append(StringUtils.join(args, " "));
        LOGGER.debug(sb.toString());
        String result = null;
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);                
            }
            result = builder.toString();
        } catch (IOException e) {
            throw new TrafficLightException(e);
        }
        logResult(result);
        return result;
    }

    private void logResult(String result) {
        try (FileWriter fw = new FileWriter(logFile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(result);
        } catch (IOException e) {
            LOGGER.error("Error when logging results", e);
        }
    }

    @Override
    public String turnOn(Color color) throws TrafficLightException {
        return executeProcess(commandPath, TURN_ON_COMMAND, color.getCode());
    }

    @Override
    public String turnOff(Color color) throws TrafficLightException {
        return executeProcess(commandPath, TURN_OFF_COMMAND, color.getCode());
    }

    @Override
    public String displayHelp() throws TrafficLightException {
        return executeProcess(commandPath, HELP_COMMAND);
    }

    public File getLogFile() {
        return logFile;
    }

    public String getCommandPath() {
        return commandPath;
    }

}
