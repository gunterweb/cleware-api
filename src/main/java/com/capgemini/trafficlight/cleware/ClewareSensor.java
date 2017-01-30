package com.capgemini.trafficlight.cleware;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.capgemini.trafficlight.Color;
import com.capgemini.trafficlight.Sensor;
import com.capgemini.trafficlight.exception.TrafficLightException;

public class ClewareSensor implements Sensor {

    private File logFile;
    private String commandPath;

    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(ClewareSensor.class);

    public ClewareSensor(String commandPath, File logFile) {
        this.setCommandPath(commandPath);
        this.setLogFile(logFile);
    }

    private void executeProcess(String... args) throws TrafficLightException {
        ProcessBuilder pb = new ProcessBuilder(args);
        LOGGER.debug("Execute command : " + StringUtils.join(args, " "));
        pb.redirectOutput(logFile);
        try {
            pb.start();
        } catch (IOException e) {
            throw new TrafficLightException(e);
        }
    }

    @Override
    public void turnOn(Color color) throws TrafficLightException {
        executeProcess(commandPath, "1", color.getCode());
    }

    @Override
    public void turnOff(Color color) throws TrafficLightException {
        executeProcess(commandPath, "0", color.getCode());
    }

    @Override
    public void displayHelp() throws TrafficLightException {
        executeProcess(commandPath, "-h");
    }

    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    public String getCommandPath() {
        return commandPath;
    }

    public void setCommandPath(String commandPath) {
        this.commandPath = commandPath;
    }

}
