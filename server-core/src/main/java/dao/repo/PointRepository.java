package dao.repo;

import dao.Point;
import org.springframework.data.repository.CrudRepository;


public interface PointRepository extends CrudRepository<Point, Integer> {
}
