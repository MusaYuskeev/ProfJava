package services;

import dao.Point;
import dao.repo.PointRepository;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import server.Main;

import java.util.List;
import java.util.logging.Logger;

@EnableJpaRepositories("dao")
@EntityScan(basePackageClasses = dao.Point.class)
@Service
public class storeService {
    public static Logger log = Logger.getLogger(Main.class.getName());

    @Autowired
    PointRepository pointRepository;

    public void addCoord(PointDTO pointDTO) {
        Point point = new Point();
        point.setAutoId(pointDTO.getAutoId());
        point.setLat(pointDTO.getLat());
        point.setLon(pointDTO.getLon());
        point.setAzimuth(pointDTO.getAzimuth());
        point.setSpeed(pointDTO.getSpeed());
        point.setTime(pointDTO.getTime());
        log.info("Received point: " + pointRepository.save(point));
    }

    public List<Point> getTracks(String idAuto) {
        return pointRepository.findByAuto_Id(idAuto);
    }
}
