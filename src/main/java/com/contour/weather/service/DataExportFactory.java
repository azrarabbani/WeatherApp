package com.contour.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Data Export Factory class to return service instance based on the given data format type.
 */
@Component
public class DataExportFactory {

    public static String CSV_FORMAT = "CSV";

    private WeatherService weatherService;

    @Autowired
    public DataExportFactory(WeatherService weatherService){
        this.weatherService = weatherService;
    }
    public DataExportService getDataExportService (String type){
        validate(type);
        if(type.equalsIgnoreCase(CSV_FORMAT)) {
            return new CSVDataExportServiceImpl(weatherService);
        } else {
            throw new IllegalArgumentException("Data export type "+type+ " is not supported.");
        }

    }

    private void validate(String type){
        Assert.notNull(type, "Data Export type parameter can not be null.");
        Assert.isTrue(type.length() > 0, "Data Export type parameter can not be empty.");
    }

}
