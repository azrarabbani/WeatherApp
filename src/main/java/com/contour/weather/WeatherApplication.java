package com.contour.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This is a main application class which enables various configurations for the weather application
 */
@SpringBootApplication
@EnableWebSecurity
@EnableCaching
@EnableWebMvc
@EnableScheduling
public class WeatherApplication {

    private static Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

    public static void main(String[] args) {
        logger.info("Welcome to Weather Test Application .....");
        SpringApplication.run(WeatherApplication.class, args);
    }
}
