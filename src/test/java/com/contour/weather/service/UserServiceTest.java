package com.contour.weather.service;

import com.contour.weather.data.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * User service test
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class UserServiceTest {

    private Logger logger  = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    public void setup(){

    }

    //@Test
    public void testAddNewUSer(){
        logger.info("Add new User .... ");
        userService.addNewUSerInMemory("azra@go.com", "password");
        //todo it should be verified against ldap repo

        logger.info("Test successfull .... ");
    }
}
