
package jdev.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"tracker", "services"})

public class Main {

    public static void main(String[] args) {
        SpringApplication.run(SchedulingContext.class, args);

    }


}
