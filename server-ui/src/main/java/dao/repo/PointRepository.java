package dao.repo;

import dao.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PointRepository extends CrudRepository<Point, Integer> {

    @Query("select p from Point p where p.autoId = ?1 order by p.time desc")
    List<Point> findByAuto_Id(String autoId);
}
