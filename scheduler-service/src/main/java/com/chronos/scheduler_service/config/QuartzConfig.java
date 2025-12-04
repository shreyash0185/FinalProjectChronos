package com.chronos.scheduler_service.config;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        return new CustomJobFactory(applicationContext.getAutowireCapableBeanFactory());
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory);
        factory.setDataSource(dataSource);  // Spring Boot datasource

        // Quartz properties
        Properties props = new Properties();
        props.setProperty("org.quartz.scheduler.instanceId", "AUTO");

        // JDBC JobStore
        props.setProperty("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        props.setProperty("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        props.setProperty("org.quartz.jobStore.tablePrefix", "QRTZ_");

        // IMPORTANT: Tell Quartz which datasource to use
        props.setProperty("org.quartz.jobStore.dataSource", "quartzDataSource");

        // Define datasource configuration for Quartz
        props.setProperty("org.quartz.dataSource.quartzDataSource.driver", "com.mysql.cj.jdbc.Driver");
        props.setProperty("org.quartz.dataSource.quartzDataSource.URL",
                "jdbc:mysql://localhost:3306/chronos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        props.setProperty("org.quartz.dataSource.quartzDataSource.user", "root");
        props.setProperty("org.quartz.dataSource.quartzDataSource.password", "Shreyash@0185");
        props.setProperty("org.quartz.dataSource.quartzDataSource.maxConnections", "10");

        // Thread pool
        props.setProperty("org.quartz.threadPool.threadCount", "10");

        factory.setQuartzProperties(props);

        return factory;
    }
}