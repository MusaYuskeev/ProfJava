package controllers;

import jdev.dto.PointDTO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.storeService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PostControllerTest {

    @Mock
    storeService storeService;

    @InjectMocks
    PostController mockedController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCoords() throws IOException {
        // PostController controller = new PostController();

        PointDTO point = new PointDTO();
        //    point = null;
        //   when(storeService.addCoord(point)).thenReturn(new Response());
        // Response result = mockedController.relay();
        Response result = mockedController.getCoords(point);
        assertEquals("ok", result.message);
        assertEquals(true, result.success);

        point = null;
        result = mockedController.getCoords(point);
        assertEquals("fail", result.message);
        assertEquals(false, result.success);
    }
}