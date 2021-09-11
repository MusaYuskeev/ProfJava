package jdev.services;

import jdev.dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class dataStoreServiceTest {
    BlockingDeque<PointDTO> newQueue = new LinkedBlockingDeque<>(100);
    @Mock
    dataSendService dataSendService;
    @InjectMocks
    dataStoreService mockedstoreService;

    @Before
    public void setUp() throws InterruptedException {
        PointDTO point;

        point = new PointDTO();
        point.setLat(1);
        point.setLon(1);
        newQueue.put(point);

        point = new PointDTO();
        point.setLat(2);
        point.setLon(2);
        newQueue.put(point);

    }

    @Test
    public void savePoints() throws InterruptedException {
        dataStoreService service = new dataStoreService();
        service.savePoints(newQueue);
        assertEquals(0, newQueue.size());
    }

    @Test
    public void sendData() throws IOException, InterruptedException {
        mockedstoreService.savePoints(newQueue);
        mockedstoreService.sendData();
    }
}