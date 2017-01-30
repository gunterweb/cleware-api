package com.capgemini.trafficlight;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;

import com.capgemini.trafficlight.cleware.ClewareSensor;
import com.capgemini.trafficlight.exception.TechnicalException;
import com.capgemini.trafficlight.utils.PropertyHelper;

/**
 * Sensor factory <br>
 * 
 * @author fbontemp
 *
 */
public class SensorFactory implements Serializable {

    private static File logFile;
    private static String commandPath;
    /**
     * 
     */
    private static final long serialVersionUID = -4809776117038846332L;

    private static final String LOG_PATH = "log.path";
    private static final String COMMAND_PATH = "command.path";

    /**
     * Singleton instance
     */
    private static SensorFactory instance;

    private SensorFactory() {
    }

    /**
     * Get current Factory instance
     * 
     * @return PartyFactory instance
     */
    public static SensorFactory getInstance() throws TechnicalException {
        if (instance == null) {
            instance = new SensorFactory();
            SensorFactory.init();
        }
        return instance;
    }

    /**
     * Sensor initialization
     */
    private static void init() throws TechnicalException {
        logFile = Paths.get(PropertyHelper.getProperty(LOG_PATH)).toFile();
        commandPath = PropertyHelper.getProperty(COMMAND_PATH);
    }

    /**
     * Creates a sensor
     * 
     * @return Sensor
     */
    public Sensor createSensor() {
        return new ClewareSensor(commandPath, logFile);
    }
}
