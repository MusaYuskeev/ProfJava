package controllers;

import jdev.dto.PointDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import services.storeService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    @Mock
    storeService storeService;

    @InjectMocks
    PostController mockedController;

    @Test
    public void getCoords() throws IOException {

        PointDTO point = new PointDTO();
        Response result = mockedController.getCoords(point);
        assertEquals("ok", result.getMessage());
        assertEquals(true, result.isSuccess());

        point = null;
        result = mockedController.getCoords(point);
        assertEquals("fail", result.getMessage());
        assertEquals(false, result.isSuccess());
    }
}