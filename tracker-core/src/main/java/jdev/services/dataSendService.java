package jdev.services;

import jdev.dto.PointDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.logging.Logger;

@Service
public class dataSendService {
    private static Logger log = Logger.getLogger(gpsService.class.getName());

    // Отправляем очередь на server-core
    void sendData(BlockingDeque<PointDTO> queue) throws InterruptedException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        log.info("--------->   Sending data to the server. Data count = " + queue.size());

        //выбираем все данные из очереди (сохраненные данные) и отправляем на server-core
        while (queue.size() > 0) {
            PointDTO responseData = restTemplate.postForObject("http://localhost:8080/coords", queue.poll(), PointDTO.class);
            log.info("Sent point: " + responseData + " Points to be send: " + queue.size());
        }
    }
}


