package com.capgemini.trafficlight.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.capgemini.trafficlight.api")
public class App {

    private App() {
        super();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

}
