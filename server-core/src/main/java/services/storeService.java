package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import server.Main;

@Service
public class storeService {


    public void addCoord(String point) throws JsonProcessingException {
        Main.log.info("Received point: " + point);

    }

}
