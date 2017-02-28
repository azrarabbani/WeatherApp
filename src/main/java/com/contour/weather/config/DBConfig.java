package com.contour.weather.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
import javax.sql.DataSource;


/**
 * Configuration class for Database reads properties from resource file
 */
@Configuration
public class DBConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String pwd;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    private Logger logger = LoggerFactory.getLogger(DBConfig.class);

    @Bean
    public DataSource dataSource() {
        logger.info("DataSource is initializing ... "+url +", "+username+", " + driver);
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        return dataSource;
    }

}
