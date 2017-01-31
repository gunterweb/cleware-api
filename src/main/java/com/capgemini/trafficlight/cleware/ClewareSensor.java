package com.capgemini.trafficlight.cleware;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.capgemini.trafficlight.Color;
import com.capgemini.trafficlight.Sensor;
import com.capgemini.trafficlight.exception.TrafficLightException;

@Component
public class ClewareSensor implements Sensor {

    @Value("${log.path}")
    private File logFile;
    @Value("${command.path}")
    private String commandPath;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareSensor.class);

    private void executeProcess(String... args) throws TrafficLightException {
        ProcessBuilder pb = new ProcessBuilder(args);
        LOGGER.debug("Execute command : " + StringUtils.join(args, " "));
        pb.redirectOutput(Redirect.appendTo(logFile));
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

    public String getCommandPath() {
        return commandPath;
    }

}
