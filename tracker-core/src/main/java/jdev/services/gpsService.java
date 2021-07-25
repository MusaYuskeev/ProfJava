package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

@Service
public class gpsService {

    @Autowired
    private DataStoreService dataStoreService;

    private static Logger log = Logger.getLogger(gpsService.class.getName());
    private BlockingDeque<String> queue = new LinkedBlockingDeque<>(10);
    private int count = 0;

    // get gps data from source (array, file etc.)
    @Scheduled(fixedDelay = 500)
    void getPoint() throws InterruptedException, JsonProcessingException {

        PointDTO point = new PointDTO();
        point.setLat(56 + count);
        point.setLon(74 + count);
        point.setAutoId(String.format("a%1$d%1$d%1$dpm", count));
        point.setTime(System.currentTimeMillis());
        count++;
        log.info("get new point " + count + ' ' + point.toJson());
        queue.put(point.toJson());
    }

    //save each data point in store queue
    @Scheduled(fixedDelay = 2000)
    void storePoint() throws InterruptedException {
        log.info("store point " + count);
        dataStoreService.savePoint(queue);
    }
}
