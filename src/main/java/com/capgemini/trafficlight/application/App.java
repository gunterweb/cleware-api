package com.capgemini.trafficlight.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main application
 * 
 * @author fbontemp
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.capgemini.trafficlight.impl,com.capgemini.trafficlight.api.impl,com.capgemini.trafficlight.controller")
public class App {

    /**
     * Main method
     * 
     * @param args
     *            args
     * @throws Exception
     *             Error thrown
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
