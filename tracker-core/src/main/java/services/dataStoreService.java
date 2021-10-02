package services;

import dao.Point;
import dao.repo.PointRepository;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingDeque;
import java.util.logging.Logger;


@Service
public class dataStoreService {
    private static Logger log = Logger.getLogger(gpsService.class.getName());
    //  @Autowired
    //  private dataSendService dataSendService;

    @Autowired
    PointRepository pointRepository;

    //    private BlockingDeque<PointDTO> store_queue = new LinkedBlockingDeque<>(100);

    void savePoint(BlockingDeque<PointDTO> queue) throws InterruptedException {
        while (queue.size() > 0) {
            PointDTO poll = queue.poll();
            Point new_point = new Point();
            new_point.setAutoId(poll.getAutoId());
            new_point.setLat(poll.getLat());
            new_point.setLon(poll.getLon());
            new_point.setAzimuth(poll.getAzimuth());
            new_point.setSpeed(poll.getSpeed());
            new_point.setTime(poll.getTime());
            pointRepository.save(new_point);
            log.info("Storing in queue:" + 1);
        }
    }


}

