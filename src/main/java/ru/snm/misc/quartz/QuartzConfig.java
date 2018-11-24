package ru.snm.misc.quartz;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author sine-loco
 */
@Configuration
@ConfigurationProperties( "timeouts" )
public class QuartzConfig {
    private int first;

    private int second;

    public void setFirstSeconds( String first ) {
        this.first = Integer.parseInt( first );
    }

    public void setSecondSeconds( String second ) {
        this.second = Integer.parseInt( second );
    }

    @Bean
    public JobFactory jobFactory( ApplicationContext applicationContext ) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext( applicationContext );
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
            JobFactory jobFactory, Properties quartzProperties )
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs( true );
        factory.setAutoStartup( true );
        //This is the place where we will wire Quartz and Spring together
        factory.setJobFactory( jobFactory );
        factory.setConfigLocation( new ClassPathResource( "quartz.properties" ) );
        //factory.setTriggers(triggers);
        return factory;
    }
    

}
