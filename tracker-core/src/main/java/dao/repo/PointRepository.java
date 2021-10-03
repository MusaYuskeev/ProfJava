package dao.repo;

import dao.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {

    List<Point> findByIsSendFalse();


    @Query("select p from Point p where p.autoId = ?1")
    List<Point> findByAuto_IdOrderByTimeDesc(String autoId);
}
