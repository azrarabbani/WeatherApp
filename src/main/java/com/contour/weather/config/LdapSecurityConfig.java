package com.contour.weather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Arrays;


/**
 * LDAP Security Configuration, reads properties from resource file
 *
 *
 * LDAP Basics
 *
 Dn - Distinguished name, a unique name which is used to find the user in LDAP server e.g. Microsoft Active Directory.

 Ou - Organization Unit

 Bind - LDAP Bind is an operation in which LDAP clients sends bindRequest to LDAP user including username and password
 and if LDAP server able to find user and password correct, it allows access to the ldap server.

 Search - LDAP search is an operation which is performed to retrieve Dn of the user by using some user credential.

 Root - LDAP directory's top element, like Root of a tree.

 BaseDn - a branch in LDAP tree which can be used as base for LDAP search operation e.g. dc=Microsoft,dc=org"

 */
//@Configuration
public class LdapSecurityConfig extends WebSecurityConfigurerAdapter {

    //following are for ldap based authentication
    @Value("${spring.ldap.url}")
    private String ldapUrl;

    @Value("${spring.ldap.embedded.base-dn}")
    private String ldapBaseDn;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
        http
                .authorizeRequests().antMatchers("/resources/**","/login/addNewUser","/css/**","/images/**")
                .permitAll().anyRequest().permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(Arrays.asList(ldapUrl),
                ldapBaseDn);//"dc=springframework,dc=org"
    }


    @Bean("ldapTemplate")
    public LdapTemplate ldapTemplate() throws Exception{
        LdapTemplate ldapTemplate = new LdapTemplate(contextSource());
//        try {
//            source.afterPropertiesSet();
//            source.getReadWriteContext();
//            return true;
//        }
//        catch(Exception e) {
//            return false;
//        }
        ldapTemplate.setIgnorePartialResultException(true);
        return ldapTemplate;
    }

//    public LdapContextSource  getContextSource() throws Exception{
//        LdapContextSource ldapContextSource = new LdapContextSource();
//        ldapContextSource.setUrl(ldapUrl);
//        ldapContextSource.setBase(ldapBaseDn);
//        ldapContextSource.setUserDn("uid=ben,ou=people");//
//        ldapContextSource.setPassword("benspassword");
//        return ldapContextSource;
//    }


}
