package ru.snm.misc.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author sine-loco
 */
@SpringBootApplication
@EnableConfigurationProperties
public class SpringQuartzMain {
    public static void main( String[] args ) {
        SpringApplication.run( SpringQuartzMain.class, args );
    }
}
