package services;

import dao.Point;
import dao.repo.PointRepository;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import server.Main;

import java.util.logging.Logger;

@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = dao.Point.class)
@Service
public class storeService {
    public static Logger log = Logger.getLogger(Main.class.getName());

    @Autowired
    PointRepository pointRepository;

    public void addCoord(PointDTO point) {
        Point new_point = new Point();
        new_point.setAutoId(point.getAutoId());
        new_point.setLat(point.getLat());
        new_point.setLon(point.getLon());
        new_point.setAzimuth(point.getAzimuth());
        new_point.setSpeed(point.getSpeed());
        new_point.setTime(point.getTime());


        log.info("Received point: " + pointRepository.save(new_point));
    }

}
