package com.contour.weather.service;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.exception.DataExportException;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

/**
 * Abstract class provides basic weather data related functionality, export specific implementation should
 * be provided by the sub classes
 */
@Service
public abstract class AbstractDataExportServiceImpl implements DataExportService{

    private WeatherService weatherService;

    public AbstractDataExportServiceImpl(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    public abstract void exportData(PrintWriter writer) throws DataExportException;


    protected List<WeatherUpdate> getWeatherInfo(){
        return this.weatherService.findAll();
        //mocking data
        // List<WeatherUpdate> weatherUpdateList = new ArrayList<>();
//        WeatherUpdate wc = new WeatherUpdate();
//        wc.setTemperature(30.7);
//        wc.setFeelsLike(22);
//        wc.setLocation(new Location("USA", "CA", "Fremont"));
//        wc.setWeatherCondition(WeatherCondition.CLEAR);
//        weatherUpdateList.add(wc);
        //return weatherUpdateList;
    }
}
