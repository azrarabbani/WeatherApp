package com.contour.weather.controller;

import com.contour.weather.data.model.User;
import com.contour.weather.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller handles signup related requests
 */
@Controller
public class SignupController {

    private Logger logger = LoggerFactory.getLogger(SignupController.class);

    private UserService userService;

    @Autowired
    public SignupController(UserService userService){
        this.userService = userService;
    }


//    @RequestMapping(value = { "/login/addNewUser" }, method = RequestMethod.POST)
//    public ModelAndView addNewUserToLdap(@RequestParam("email") String email,
//                                   @RequestParam("name") String name,
//                                   @RequestParam("password") String password ) {
//        logger.info("Adding new user....");
//        //todo: input validation
//        User user = new User(email, name, password);
//        userService.addNewLdapUser(user);
//        ModelAndView model = new ModelAndView();
//        model.addObject("signupsuccess", "Sign up successful. Please login with your credentials.");
//        model.setViewName("login");
//        return model;
//    }

    @RequestMapping(value = { "/login/addNewUser" }, method = RequestMethod.POST)
    public ModelAndView addNewUser(@RequestParam("email") String email,
                         @RequestParam("name") String name,
                         @RequestParam("password") String password ) {
        User user = new User(email, name, password);
        userService.addNewUSerInMemory(email, password);
        ModelAndView model = new ModelAndView();
        model.addObject("signupsuccess", "Sign up successful. Please login with your credentials.");
        model.setViewName("login");
        return model;
    }
}
