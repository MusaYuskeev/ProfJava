package jdev.services;

import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

@Service
public class DataStoreService {
    private static Logger log = Logger.getLogger(gpsService.class.getName());
    @Autowired
    private DataSendService dataSendService;
    private BlockingDeque<PointDTO> store_queue = new LinkedBlockingDeque<>(100);

    void savePoint(BlockingDeque<PointDTO> queue) throws InterruptedException {
        while (queue.size() > 0) {
            PointDTO poll = queue.poll();
            log.info("Storing in queue:" + poll + " Store size:" + queue.size());
            store_queue.put(poll);
        }
    }

    // Отправляем очередь на server-core
    @Scheduled(cron = "${sendDataCron}")
    void sendData() throws InterruptedException, IOException {
        log.info("Send stored queue to server");
        dataSendService.sendData(store_queue);
    }
}

