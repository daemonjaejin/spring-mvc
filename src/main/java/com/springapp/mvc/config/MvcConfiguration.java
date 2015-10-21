package com.springapp.mvc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by jin on 15. 10. 13..
 */
@EnableWebMvc //mvc:annotation-driven
@EnableAsync
@EnableTransactionManagement
@Configuration
@ComponentScan(
        basePackages = "com.springapp.mvc"
        , excludeFilters = @ComponentScan.Filter(Configuration.class)
)
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

    /**
     * @see org.springframework.jndi.JndiObjectFactoryBean
     * */
    @Bean(name = "factoryBean")
    public JndiObjectFactoryBean getFactoryBean(){
        logger.info("getFactoryBean!!!");
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("java:comp/env/jdbc/connect2");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setExpectedType(DataSource.class);
        return jndiObjectFactoryBean;
    }

    /**
     * @see org.springframework.orm.hibernate4.LocalSessionFactoryBean
     * */
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean getSessionFactoryBean(){
        logger.info("getSessionFactoryBean!!!");
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource((DataSource)getFactoryBean().getObject());
        factoryBean.setPackagesToScan("com.springapp.mvc.domain");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.show_sql", "true");
        factoryBean.setHibernateProperties(properties);
        return factoryBean;
    }

    /**
     * @see org.hibernate.Hibernate
     * */
    @Bean(name = "txName")
    public HibernateTransactionManager getTransactionManagerHibernate() {
        logger.info("getTransactionManagerHibernate!!!");
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.getSessionFactoryBean().getObject());
        return transactionManager;
    }

    /**
     * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
     * */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(){
        logger.info("getTransactionManager!!!");
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getDataSource());
        return transactionManager;
    }

    /**
     * @see javax.sql.DataSource
     * */
    @Bean(name = "dataSource")
    public DataSource getDataSource(){
        logger.info("getDataSource!!!");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://queryjet.iptime.org:23306/hornetdb?useUnicode=yes&amp;characterEncoding=UTF-8&amp;autoReconnect=true");
        dataSource.setUsername("hornet_user");
        dataSource.setPassword("znjflwpt!!");
        dataSource.setMaxActive(100);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("select 1");
        return dataSource;
    }

}
