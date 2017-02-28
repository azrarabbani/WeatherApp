package com.contour.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import org.springframework.ldap.core.DistinguishedName;

import java.util.ArrayList;

/**
 * User specific service to expose API related to user operations, e.g. adding a new user to ldap
 */
@Service
public class UserService {

    private static final String TOP = "top";
    private static final String UID_OBJECT = "uidObject";
    private static final String PERSON = "person";
    private static final String ORGANIZATIONAL_PERSON = "organizationalPerson";
    private static final String OBJECT_CLASS = "objectClass";
    private static final String SURNAME = "surname";
    public static final String CN = "cn";
    public static final String SN = "sn";
    public static final String UID = "uid";
    private Logger logger = LoggerFactory.getLogger(UserService.class);

//    @Autowired
//    private LdapTemplate ldapTemplate;

    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public UserService(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

//    public boolean addNewLdapUser(@RequestBody com.contour.weather.data.model.User user) {
//        logger.info("Adding new user to Ldap....." +user);
//        Attribute objectClass = new BasicAttribute(OBJECT_CLASS);
//        {
//            objectClass.add(TOP);
//            objectClass.add(UID_OBJECT);
//            objectClass.add(PERSON);
//            objectClass.add(ORGANIZATIONAL_PERSON);
//        }
//        Attributes userAttributes = new BasicAttributes();
//        userAttributes.put(objectClass);
//        userAttributes.put(CN,user.getName());
//        userAttributes.put(SN, SURNAME);
//        userAttributes.put(UID, user.getEmail());
//        userAttributes.put("userPassword", user.getPassword().getBytes());
//        ldapTemplate.bind(bindDN(user.getEmail()), null, userAttributes);
//        logger.info("Added user to Ldap.....");
//        return true;
//    }

    public static javax.naming.Name bindDN(String _x){
        @SuppressWarnings("deprecation")
        javax.naming.Name name = new DistinguishedName("uid=" + _x + ",ou=people");
        return name;
    }

    public boolean userExists(String username ) {
        return inMemoryUserDetailsManager.userExists(username);
    }

    public String addNewUSerInMemory(String username, String password) {
        logger.info("Adding new user in memory.... ");
        User user = new User(username, password, new ArrayList<GrantedAuthority>());
        //user.
        inMemoryUserDetailsManager.createUser(user);
        return "added";
    }

}
