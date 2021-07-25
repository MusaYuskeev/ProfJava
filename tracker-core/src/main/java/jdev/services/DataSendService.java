package jdev.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.logging.Logger;

@Service
public class DataSendService {
    private static Logger log = Logger.getLogger(gpsService.class.getName());

    // Отправляем очередь на server-core
    void sendData(BlockingDeque<String> queue) throws InterruptedException, IOException {
        log.info("sending data to server");
        while (queue.size() > 0) {
            String poll = queue.poll();

            log.info("sent point:" + poll + " Points to be send: " + queue.size());
        }
    }
}


