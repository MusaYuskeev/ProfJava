package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

@Service
public class gpsService {
    private static final Random random = new Random();
    private static Logger log = Logger.getLogger(gpsService.class.getName());
    @Autowired
    private dataStoreService dataStoreService;
    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<>(100);
    private int count = 0;

    // get gps data from source (array, file etc.)
    @Scheduled(cron = "${gpsDataCron}")
    void getPoint() throws InterruptedException, JsonProcessingException {

        PointDTO point = new PointDTO();
//      55.344070, 86.108937 Kemerovo coordinates
        point.setLat(55.344229 + count / 100.0);
        point.setLon(86.101544 + count / 100.0);
        point.setAutoId(String.format("a%1$03dpm", count));
        point.setTime(System.currentTimeMillis());
        point.setSpeed(100 * random.nextDouble());
        point.setAzimuth(count);
        count++;
        log.info("get new point " + count + ' ' + point);
        queue.put(point);
    }

    //save each data point in store queue
    @Scheduled(cron = "${storeDataCron}")
    void storePoint() throws InterruptedException {
        log.info("store point " + count);
        dataStoreService.savePoints(queue);
    }
}
