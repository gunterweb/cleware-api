package com.capgemini.trafficlight.api;

import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.trafficlight.api.dto.TrafficResponse;
import com.capgemini.trafficlight.exception.TrafficLightException;

/**
 * Interface defining TrafficService
 * 
 * @author fbontemp
 *
 */
public interface TrafficService {
    /**
     * Display Help message
     * 
     * @return String
     * @throws TrafficLightException
     *             TrafficLightException
     */

    public TrafficResponse home() throws TrafficLightException;

    /**
     * Turns on a color
     * 
     * @param color
     *            color
     * @return TrafficResponse
     * @throws TrafficLightException
     *             TrafficLightException
     */
    public TrafficResponse turnOn(@NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color)
            throws TrafficLightException;

    /**
     * Turns off a color
     * 
     * @param color
     *            color
     * @return TrafficResponse
     * @throws TrafficLightException
     *             TrafficLightException
     */
    public TrafficResponse turnOff(@NotEmpty @Length(min = 1, max = 1) @Valid @RequestParam String color)
            throws TrafficLightException;
}
