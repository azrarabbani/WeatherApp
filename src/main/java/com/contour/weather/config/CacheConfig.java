package com.contour.weather.config;

import com.contour.weather.data.repository.WeatherUpdateRepo;
import net.sf.ehcache.config.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for caching
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(CacheConfig.class);
    private static  String STORE_EVICTION_POLICY = "LRU";

    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {
        logger.info("ehcacheManager is initializing.......");
        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        CacheConfiguration weatherCacheConfig = new CacheConfiguration();
        weatherCacheConfig.setName(WeatherUpdateRepo.WEATHER_CACHE_NAME);
        weatherCacheConfig.setMemoryStoreEvictionPolicy(STORE_EVICTION_POLICY);
        weatherCacheConfig.setMaxEntriesLocalHeap(1000);
        config.addCache(weatherCacheConfig);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
