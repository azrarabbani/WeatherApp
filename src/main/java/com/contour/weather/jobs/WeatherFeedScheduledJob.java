package com.contour.weather.jobs;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.service.WeatherDataCollectionService;
import com.contour.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is a scheduled job which is supposed to collect live weather update from external application
 * and store it.
 */
@Component
public class WeatherFeedScheduledJob implements ScheduledJob{

    private Logger logger = LoggerFactory.getLogger(WeatherFeedScheduledJob.class);

    private WeatherDataCollectionService weatherDataCollectionService;
    private WeatherService weatherService;

    @Autowired
    public WeatherFeedScheduledJob(
            WeatherService weatherService,
            WeatherDataCollectionService weatherDataCollectionService){
        this.weatherDataCollectionService = weatherDataCollectionService;
        this.weatherService = weatherService;
    }

    @Override
    @Scheduled(cron = "${weather.feed.frequent.cron}")
    public void run() {
        logger.info("Running scheduled job to fetch weather updates....");
        List<WeatherUpdate> weatherLiveDataUpdates = weatherDataCollectionService.fetchLiveData();
        for (WeatherUpdate weather :
                weatherLiveDataUpdates) {
            weatherService.saveWeather(weather);
        }
        logger.info("Finished weather data collection...");
    }


}
