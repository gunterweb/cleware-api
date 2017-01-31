package com.capgemini.trafficlight.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trafficlight.Color;
import com.capgemini.trafficlight.Sensor;
import com.capgemini.trafficlight.exception.FunctionalException;
import com.capgemini.trafficlight.exception.TrafficLightException;

/**
 * Main controller
 * 
 * @author fbontemp
 *
 */
@RestController
public class TrafficController {

    @Value("${debug}")
    private boolean debug;

    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrafficController.class);

    @Autowired
    Sensor sensor;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        try {
            sensor.displayHelp();
        } catch (TrafficLightException e) {
            LOGGER.error("Error in displayHelp", e);
        }
        return "displayHelp invoked";
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TrafficResponse> turnOn(@NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color)
            throws TrafficLightException {
        Color col = null;
        try {
            col = checkColor(color);
            sensor.turnOn(col);
        } catch (TrafficLightException e) {
            LOGGER.error("Error in turnOn", e);
            throw e;
        }
        TrafficResponse response = new TrafficResponse();
        response.setMessage("turnOn Color " + col.getLabel() + " invoked");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TrafficResponse> turnOff(
            @NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color) throws TrafficLightException {
        Color col = null;
        try {
            col = checkColor(color);
            sensor.turnOff(col);
        } catch (TrafficLightException e) {
            LOGGER.error("Error in turnOff", e);
            throw e;
        }
        TrafficResponse response = new TrafficResponse();
        response.setMessage("turnOff Color " + col.getLabel() + " invoked");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Color checkColor(String color) throws FunctionalException {
        return Color.getColorFromCode(color);
    }

    @ExceptionHandler(TrafficLightException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorJson functionalError(TrafficLightException e) {
        Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("message", e.getInternationalizedMessage());
        errorAttributes.put("timestamp", new Date());
        if (debug) {
            StringWriter stackTrace = new StringWriter();
            e.printStackTrace(new PrintWriter(stackTrace));
            stackTrace.flush();
            errorAttributes.put("trace", stackTrace.toString());
        }
        return new ErrorJson(HttpStatus.BAD_REQUEST.value(), errorAttributes);
    }
}
