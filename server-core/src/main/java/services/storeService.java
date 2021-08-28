package services;

import jdev.dto.PointDTO;
import org.springframework.stereotype.Service;
import server.Main;

import java.util.logging.Logger;

@Service
public class storeService {
    public static Logger log = Logger.getLogger(Main.class.getName());

    public void addCoord(PointDTO point) {
        log.info("Received point: " + point);
    }

}
