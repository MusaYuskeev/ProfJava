package jdev.tracker;

import jdev.services.DataSendService;
import jdev.services.DataStoreService;
import jdev.services.gpsService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Configuration
@PropertySource("classpath:/app.properties")
@Component
@EnableAutoConfiguration
public class SchedulingContext {
    //emulate work of GPS Tracker
    @Bean
    public gpsService gpsService() {
        return new gpsService();
    }

    //local service to store incoming data
    @Bean
    DataStoreService storeService() {
        return new DataStoreService();
    }

    //put gps data to server
    @Bean
    DataSendService sendService() {
        return new DataSendService();
    }
}