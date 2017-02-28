package com.contour.weather.service;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.data.repository.WeatherUpdateRepo;
import com.contour.weather.util.WeatherCondition;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for weather service
 */

public class WeatherServiceTest {

    public static final String COUNTRY = "USA";
    public static final String STATE = "CA";
    public static final String CITY = "San Francisco";
    public static final double TEMPERATURE = 32.4;
    private Logger logger = LoggerFactory.getLogger(WeatherServiceTest.class);
    private WeatherService service;
    private WeatherUpdateRepo weatherUpdateRepo;

    @BeforeClass
    public void setup(){
        logger.info("Setup Weather Controller Test...");
        weatherUpdateRepo = Mockito.mock(WeatherUpdateRepo.class);
        service = new WeatherService(weatherUpdateRepo);
    }

    @Test
    public void testWeatherData(){
        logger.info("Testing weather service get data by location ...");
        List<WeatherUpdate> mockedOutput = createWeatherList();
        setupMockEnvironment(mockedOutput, weatherUpdateRepo);
        List<WeatherUpdate> actualOutput = service.getWeatherUpdateByLocation(COUNTRY, STATE, CITY);
        Assert.assertNotNull(actualOutput);
        Assert.assertTrue(actualOutput.get(0).getCountry().equals(mockedOutput.get(0).getCountry()),
                "Country doesn't match the expected value.");
        //todo: assert for rest of the values similarly
        logger.info("Test Successful...");
    }

    private List<WeatherUpdate> createWeatherList() {
        List<WeatherUpdate> weatherUpdates = new ArrayList<>(1);
        WeatherUpdate weatherUpdate = new WeatherUpdate();
        weatherUpdate.setCity( CITY);
        weatherUpdate.setCountry(COUNTRY);
        weatherUpdate.setState(STATE);
        weatherUpdate.setTemperature(TEMPERATURE);
        weatherUpdate.setWeatherCondition(WeatherCondition.MOSTLY_CLOUDY);
        weatherUpdates.add(weatherUpdate);
        return weatherUpdates;
    }

    private void setupMockEnvironment(List<WeatherUpdate> response, WeatherUpdateRepo repo){
        Mockito.doReturn(response)
                .when(repo).fetchByLocation(
                  Matchers.anyString()
                  , Matchers.anyString()
                  , Matchers.anyString()
                );
    }
}
