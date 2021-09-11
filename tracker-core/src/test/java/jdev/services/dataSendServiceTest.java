package jdev.services;

import jdev.dto.PointDTO;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class dataSendServiceTest {
    PointDTO point = new PointDTO();
    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<>(100);

    @Test
    public void sendData() throws InterruptedException, IOException {
        point.setLat(1);
        point.setLon(1);
        queue.put(point);
        dataSendService sendService = new dataSendService();
        sendService.sendData(queue);

        assertEquals(0, queue.size());
    }


    @Test
    public void sendDataRest() throws InterruptedException, IOException {
        RestTemplate restTemplate = new RestTemplate();
        point.setAzimuth(5);
        HttpEntity request = new HttpEntity(point);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PointDTO> httpEntity = new HttpEntity(point, headers);
        PointDTO responseData = restTemplate.postForObject("http://localhost:8080/coords", httpEntity, PointDTO.class);
        assertNotNull(responseData);

        ResponseEntity<PointDTO> responseEntity = restTemplate.exchange("http://localhost:8080/coords", HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<PointDTO>() {
                });
        assertNotNull(responseEntity);

    }
}