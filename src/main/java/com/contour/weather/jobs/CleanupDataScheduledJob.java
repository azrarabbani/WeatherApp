package com.contour.weather.jobs;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.service.WeatherDataCollectionService;
import com.contour.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.List;

/**
 * Created by ark on 2/27/2017.
 */
public class CleanupDataScheduledJob implements ScheduledJob{

    private Logger logger = LoggerFactory.getLogger(CleanupDataScheduledJob.class);

    private WeatherService weatherService;

    @Autowired
    public CleanupDataScheduledJob(
            WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    @Scheduled(cron = "${weather.data.cleanup.cron}")
    public void run() {
        logger.info("Running scheduled job to fetch weather updates....");
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_MONTH, -8);//
        weatherService.cleanupWeather(today.getTimeInMillis());
        logger.info("Finished weather data collection...");
    }
}
