package com.springapp.mvc.config;

import com.springapp.mvc.security.CustomTokenRepository;
import com.springapp.mvc.security.LoginFailureHandler;
import com.springapp.mvc.security.LoginSuccessHandler;
import com.springapp.mvc.security.RepositoryUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationServiceLocator;

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

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    public RepositoryUserDetailsService constructor(){
        return new RepositoryUserDetailsService();
    }

    @Autowired
    public CustomTokenRepository tokenRepository(){
        return new CustomTokenRepository();
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

    /**
     * @see org.springframework.security.config.annotation.web.builders.HttpSecurity
     * */
    @Override
    public void configure(HttpSecurity http) throws Exception{
        logger.info("configure!!!");
        http
            .formLogin()
                .loginProcessingUrl("/authentication")
                .loginPage("/account/login")
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/")
                .and()
            .rememberMe()
                .rememberMeServices(rememberMeServices())
                .key("testKeyForBlog")
                .and()
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/mypage/**").hasAnyRole("USER", "ADMIN") // ROLE_USER _ 는 사용할 수 없다.
                .antMatchers("/console/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            //.addFilterBefore(, BasicAuthenticationFilter.class)
            .sessionManagement()
                .maximumSessions(4);
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices() {
        String key = "testKeyForBlog";
        return new PersistentTokenBasedRememberMeServices(key, constructor(), tokenRepository());
    }

    /*@Bean
    public SocialAuthenticationFilter socialAuthenticationFilter(AuthenticationManager authenticationManager, SocialAuthenticationServiceLocator authenticationServiceLocator) {
        SocialAuthenticationFilter socialAuthenticationFilter = new SocialAuthenticationFilter(authenticationManager, userIdSource(), usersConnectionRepository, authenticationServiceLocator);
        socialAuthenticationFilter.setSignupUrl("/user/register");
        socialAuthenticationFilter.setPostLoginUrl("/user/register");
        socialAuthenticationFilter.setConnectionAddedRedirectUrl("/user/register");
        return socialAuthenticationFilter;
    }*/

    @Bean
    public UserIdSource userIdSource() {
        return new AuthenticationNameUserIdSource();
    }

}
