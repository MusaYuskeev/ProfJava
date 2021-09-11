package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.BlockingDeque;

import static org.junit.Assert.assertEquals;

public class gpsServiceTest {

    @Test
    public void getPoint() throws JsonProcessingException, InterruptedException {
        gpsService service = new gpsService();
        service.getPoint();

        //проверка приватных членов класса, так как метод изменяет состояние класса, но не имеет возвразаемого значения
        try {
            Field field = gpsService.class.getDeclaredField("queue");
            field.setAccessible(true);
            BlockingDeque<PointDTO> storeQueue = (BlockingDeque<PointDTO>) field.get(service);

            //размер очереди должен быть равен 1
            assertEquals(1, storeQueue.size());

            //номер автомобиля
            assertEquals("a000pm", storeQueue.getFirst().getAutoId());

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}