package com.springapp.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jin on 15. 10. 13..
 */
@EnableWebMvc //mvc:annotation-driven
@Configuration
@Import({ DataBaseConfig.class })
@ComponentScan("com.springapp.mvc.*")
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    /**
     * @see org.apache.log4j.Logger
     * */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * @see org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        logger.info("addResourceHandlers!!!");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
     * */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        logger.info("configureDefaultServletHandling!!!");
        configurer.enable();
    }

    /**
     * @see org.springframework.web.servlet.config.annotation.InterceptorRegistry
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        logger.info("addInterceptors!!!");
        registry.addInterceptor(new LocaleChangeInterceptor());
    }

    /**
     * @see org.springframework.web.servlet.ViewResolver
     * */
    @Bean
    public ViewResolver getViewResolver(){
        logger.info("getViewResolver!!!");
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     * @see org.springframework.web.multipart.commons.CommonsMultipartResolver
     * */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver(){
        logger.info("CommonsMultipartResolver!!!");
        return new CommonsMultipartResolver();
    }

    /**
     * @see org.springframework.context.support.ReloadableResourceBundleMessageSource
     * */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource(){
        logger.info("getMessageSource!!!");
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("classpath:messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    /**
     * @see org.springframework.web.servlet.i18n.SessionLocaleResolver
     * */
    @Bean(name = "localeResolver")
    public SessionLocaleResolver getSessionResolver(){
        logger.info("getSessionResolver!!!");
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("ko"));
        return sessionLocaleResolver;
    }

    /**
     * @see org.springframework.http.converter.StringHttpMessageConverter
     * */
    @Bean(name = "messageConverter")
    public StringHttpMessageConverter getMessageConverter(){
        logger.info("getMessageConverter!!!");
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.valueOf("text/plain;charset=UTF-8"));
        list.add(MediaType.valueOf("text/html;charset=UTF-8"));
        messageConverter.setSupportedMediaTypes(list);
        return messageConverter;
    }

}
