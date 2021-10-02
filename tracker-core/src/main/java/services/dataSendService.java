package services;

import dao.Point;
import dao.repo.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @Scheduled(cron = "${sendDataCron}")
        // Отправляем очередь на server-core
    void sendData() throws InterruptedException, IOException {
        log.info("Send stored queue to server");
        // BlockingDeque<PointDTO> queue = null;
        RestTemplate restTemplate = new RestTemplate();
        Iterable<Point> queue = pointRepository.findAll();
        log.info("--------->   Sending data to the server. ");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        //выбираем все данные из очереди (сохраненные данные) и отправляем на server-core
        for (Point itVar : queue) {
            HttpEntity<Point> entity = new HttpEntity<Point>(itVar, headers);

            ResponseEntity<Point> response = restTemplate.exchange(
                    "http://localhost:8080/coords", HttpMethod.POST, entity, Point.class);
//            PointDTO responseData =
            //          restTemplate.postForObject("http://localhost:8080/coords", itVar, Point.class);
/*

            HttpEntity<Object> entity = new HttpEntity<>(new HttpHeaders());
            ResponseEntity<Boolean> response = restTemplate
                    .exchange("http://localhost:8080/coords",
                            HttpMethod.POST,
                            entity,
                            Boolean.class);
*/

//            log.info("Sent point: " + responseData);
            itVar.isSend(true);
            pointRepository.save(itVar);
        }
//           queue.forEach(s -> restTemplate.postForObject("http://localhost:8080/coords", s, PointDTO.class));

    }


}


