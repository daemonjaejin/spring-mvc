package com.springapp.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by jin on 15. 10. 20..
 */

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * @see org.apache.log4j.Logger
     * */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
     * */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        logger.info("configure!!!");
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    /**
     * @see org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
     * */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        logger.info("configureGlobal!!!");
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}
