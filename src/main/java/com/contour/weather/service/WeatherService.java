package com.contour.weather.service;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.data.repository.WeatherUpdateRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather Service exposes API related to weather related operations
 */
@Service
public class WeatherService {

    private Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private WeatherUpdateRepo weatherUpdateRepo;

    @Autowired
    public WeatherService(WeatherUpdateRepo weatherUpdateRepo) {
        this.weatherUpdateRepo = weatherUpdateRepo;
    }

    /**
     * Returns all records from db
     * @return
     */
    public List<WeatherUpdate> findAll() {
        logger.debug("Fetching all Weather updates...."+weatherUpdateRepo);
        final List<WeatherUpdate> weatherUpdates = new ArrayList<>();
        Iterable<WeatherUpdate> iterable =  weatherUpdateRepo.findAll();
        for (WeatherUpdate w : iterable) {
            weatherUpdates.add(w);
        }
        return weatherUpdates;
    }

    /**
     *
     */
    public List<WeatherUpdate> getWeatherUpdateByLocation( String country
            , String state
            , String city){
        logger.debug("Fetching Weather updates by location"+country+", " +state+" , "+city);
        validateInput(country, state, city);
        final List<WeatherUpdate> weatherUpdates = new ArrayList<>();
        Iterable<WeatherUpdate> iterable =  weatherUpdateRepo.fetchByLocation(country, state, city);
        for (WeatherUpdate w : iterable) {
            weatherUpdates.add(w);
        }
        return weatherUpdates;
    }

    /**
     * Save weather info
     */
    public void saveWeather(@RequestBody WeatherUpdate weatherUpdate) {
        logger.debug("Saving Weather updates...." +weatherUpdateRepo);
        weatherUpdateRepo.save(weatherUpdate);
    }

    /**
     * cleanup old data record by calling stored procedure till given time
     * @param weatherTime time till the data should be deleted
     */
    public void cleanupWeather(Long weatherTime){
        logger.debug("Clean up weather data....");
        //todo: validateInput weatherTime ;
        weatherUpdateRepo.weatherDataCleanup(weatherTime);
    }


    private void validateInput(String country
            , String state
            , String city){
        //todo : validate
    }
}
