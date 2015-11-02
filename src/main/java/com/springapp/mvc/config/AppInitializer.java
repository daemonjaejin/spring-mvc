package com.springapp.mvc.config;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by jin on 15. 10. 13..
 */
public class AppInitializer implements WebApplicationInitializer{

    /**
     * @see org.apache.log4j.Logger
     * */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * @see javax.servlet.ServletContext
     * */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("onStartup!!!");
        WebApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        this.addUtf8CharacterEncodingFilter(servletContext);
    }

    /**
     * @see org.springframework.web.context.support.AnnotationConfigWebApplicationContext
     * */
    private AnnotationConfigWebApplicationContext getContext() {
        logger.info("getContext!!!");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.springapp.mvc.config.MvcConfiguration");
        return context;
    }

    /**
     * @see javax.servlet.FilterRegistration
     * */
    private void addUtf8CharacterEncodingFilter(ServletContext servletContext){
        logger.info("addUtf8CharacterEncodingFilter!!!");
        FilterRegistration.Dynamic filter = servletContext.addFilter("CHARACTER_ENCODING_FILTER", CharacterEncodingFilter.class);
        filter.setInitParameter("encoding", "UTF-8");
        filter.setInitParameter("forceEncoding", "true");
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}
