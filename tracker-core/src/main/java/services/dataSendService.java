package services;

import dao.Point;
import dao.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
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

        List<Point> queue = pointRepository.findByIsSendFalse();

        log.info("--------->   Sending data to the server. Data count =" + queue.size());

        //выбираем все данные из очереди (сохраненные данные) и отправляем на server-core
        queue.forEach(s -> {
            restTemplate.postForObject("http://localhost:8080/coords", s, Point.class);
            s.wasSend(true);
            pointRepository.save(s);
        });

    }


}


