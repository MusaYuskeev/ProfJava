package tracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import services.dataSendService;
import services.dataStoreService;
import services.gpsService;

@EnableScheduling
//@Configuration
@PropertySource("classpath:/app.properties")
//@Component
//@EnableAutoConfiguration
public class SchedulingContext {
    //emulate work of GPS Tracker
    @Bean
    public gpsService gpsService() {
        return new gpsService();
    }

    //local service to store incoming data
    @Bean
    dataStoreService storeService() {
        return new dataStoreService();
    }

    //put gps data to server
    @Bean
    dataSendService sendService() {
        return new dataSendService();
    }
}