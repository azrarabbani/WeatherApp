package com.contour.weather.data.repository;

import com.contour.weather.data.model.WeatherUpdate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by ark on 2/25/2017.
 */
@Repository
public interface WeatherUpdateRepo extends CrudRepository<WeatherUpdate, Integer>
{
    String WEATHER_CACHE_NAME = "weatherCacheName";

    @Query(value = "SELECT * FROM weather_info WHERE  " +
            " country = ?1" +
            " and state = ?2" +
            " and city = ?3 order by weather_time desc", nativeQuery = true)//
    @Cacheable(value = WEATHER_CACHE_NAME)
    Iterable<WeatherUpdate> fetchByLocation(String country, String state, String city);

    @Override
    @CacheEvict(value = WEATHER_CACHE_NAME, allEntries = true)
    WeatherUpdate save(WeatherUpdate weather);

    @Procedure(name = "clean_weather_data")
    void weatherDataCleanup(@Param("timeToClean") Long weatherTime );
}
