package com.springapp.mvc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by jin on 15. 10. 23..
 */
@Configuration
@EnableTransactionManagement
public class DataBaseConfig {

    /**
     * @see org.apache.log4j.Logger
     * */
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * @see org.springframework.jndi.JndiObjectFactoryBean
     * */
    @Bean
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
    @Bean
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
    @Bean
    public HibernateTransactionManager getTransactionManagerHibernate() {
        logger.info("getTransactionManagerHibernate!!!");
        return new HibernateTransactionManager(getSessionFactoryBean().getObject());
    }

    /**
     * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
     * */
    /*@Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager(){
        logger.info("getTransactionManager!!!");
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getDataSource());
        return transactionManager;
    }*/

    /**
     * @see javax.sql.DataSource
     * */
    /*@Bean(name = "dataSource")
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
    }*/

}
