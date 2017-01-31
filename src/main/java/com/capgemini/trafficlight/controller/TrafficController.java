package com.capgemini.trafficlight.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.trafficlight.api.TrafficService;
import com.capgemini.trafficlight.api.dto.TrafficResponse;
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

    @Autowired
    TrafficService trafficService;

    /**
     * Display Help message
     * 
     * @return String
     * @throws TrafficLightException
     *             TrafficLightException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TrafficResponse> home() throws TrafficLightException {
        return new ResponseEntity<>(trafficService.home(), HttpStatus.OK);
    }

    /**
     * Turns on a color
     * 
     * @param color
     *            color
     * @return TrafficResponse
     * @throws TrafficLightException
     *             TrafficLightException
     */
    @RequestMapping(value = "/on", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TrafficResponse> turnOn(@NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color)
            throws TrafficLightException {
        return new ResponseEntity<>(trafficService.turnOn(color), HttpStatus.OK);
    }

    /**
     * Turns off a color
     * 
     * @param color
     *            color
     * @return TrafficResponse
     * @throws TrafficLightException
     *             TrafficLightException
     */
    @RequestMapping(value = "/off", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TrafficResponse> turnOff(
            @NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color) throws TrafficLightException {
        return new ResponseEntity<>(trafficService.turnOff(color), HttpStatus.OK);
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
