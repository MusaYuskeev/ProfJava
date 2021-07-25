package jdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

@Service
public class DataStoreService {
    @Autowired
    private DataSendService dataSendService;
    private static Logger log = Logger.getLogger(gpsService.class.getName());
    private BlockingDeque<String> store_queue = new LinkedBlockingDeque<>(10);

    void savePoint(BlockingDeque<String> queue) throws InterruptedException {
        while (queue.size() > 0) {
            String poll = queue.poll();
            log.info("Store queue:" + poll + " Store size:" + queue.size());
            store_queue.put(poll);
        }
    }

    // Отправляем очередь на server-core
    @Scheduled(fixedDelay = 3_000)
    void sendData() throws InterruptedException, IOException {
        log.info("send stored queue to server");
        dataSendService.sendData(store_queue);
    }
}

