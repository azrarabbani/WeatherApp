package com.contour.weather.controller;

import com.contour.weather.data.model.WeatherUpdate;
import com.contour.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Weather controller
 */
@RestController
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/weather", method = RequestMethod.GET)
    public ModelAndView getWeatherUpdateByLocation(@RequestParam("country")  String country
            , @RequestParam("state")  String state
            , @RequestParam("city") String city) {
        List<WeatherUpdate> weatherUpdates =
                weatherService.getWeatherUpdateByLocation(country, state, city);
        WeatherUpdate weatherUpdate;
        if(weatherUpdates != null && weatherUpdates.size() > 0) {
            weatherUpdate = weatherUpdates.get(0);
        } else {
            weatherUpdate = new WeatherUpdate();//todo : should be handled properly
        }
        ModelAndView model = new ModelAndView();
        model.addObject("weatherUpdate", weatherUpdate);
        model.setViewName("WeatherInfo");
        return model;
    }

    @RequestMapping(value = "/weather", method = RequestMethod.POST)
    public String saveWeather(@RequestBody WeatherUpdate weatherUpdate) {
        weatherService.saveWeather(weatherUpdate);
        return "Saved Successfully!";
    }

    @RequestMapping(value = "/weather/history", method = RequestMethod.GET)
    public ModelAndView getWeatherHistory() {
        //get history for default values
        ModelAndView model = new ModelAndView();
        List<WeatherUpdate> weatherUpdates =
                weatherService.getWeatherUpdateByLocation("USA", "CA", "San Francisco");
        model.addObject("weatherUpdates", weatherUpdates);
        model.setViewName("WeatherInfo");
        return model;

    }
}