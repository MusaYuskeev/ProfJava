package dao.repo;

import dao.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends CrudRepository<Point, Integer> {

    List<Point> findByIsSendFalse();


}
