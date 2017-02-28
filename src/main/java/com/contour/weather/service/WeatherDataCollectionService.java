package com.contour.weather.service;

import com.contour.weather.data.model.WeatherUpdate;;
import com.contour.weather.util.WeatherCondition;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ark on 2/27/2017.
 */
@Service
public class WeatherDataCollectionService {
    public static final String CITY = "San Francisco";
    public static final String STATE = "CA";
    public static final String COUNTRY = "USA";

    private Random random = new Random(10);
    /**
     * This method is supposed to fetch data using Rest template from some external weather web service
     * but to save time the data is being mocked here.
     * @return
     */
    public List<WeatherUpdate> fetchLiveData() {
        //todo: rest template can be used to fetch live data from external source
        //mock data
        List<WeatherUpdate> weatherUpdates = new ArrayList<>();
        WeatherUpdate weatherUpdate = new WeatherUpdate();
        weatherUpdate.setCity(CITY);
        weatherUpdate.setState(STATE);
        weatherUpdate.setCountry(COUNTRY);
        weatherUpdate.setTemperature(33);
        weatherUpdate.setWeatherCondition(WeatherCondition.MOSTLY_CLOUDY);
        weatherUpdate.setWeatherTime(System.currentTimeMillis());
        weatherUpdates.add(weatherUpdate);
        return weatherUpdates;
    }
}
