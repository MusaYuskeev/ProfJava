package services;

import dao.Point;
import dao.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@Service
public class dataSendService {
    private static Logger log = Logger.getLogger(gpsService.class.getName());

    @Autowired
    PointRepository pointRepository;

    // Отправляем очередь на server-core
    @Scheduled(cron = "${sendDataCron}")
    void sendData() throws InterruptedException, IOException {
        RestTemplate restTemplate = new RestTemplate();

        //TODO: Criteria isSend = false
        Iterable<Point> queue = pointRepository.findAll();
        log.info("--------->   Sending data to the server. ");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //выбираем все данные из очереди (сохраненные данные) и отправляем на server-core

        queue.forEach(s -> {
            restTemplate.postForObject("http://localhost:8080/coords", s, Point.class);
            s.wasSend(true);
            pointRepository.save(s);
        });

    }


}


