package lee.vioson.nicepicservice.repository;

import lee.vioson.nicepicservice.models.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicRepository extends JpaRepository<Pic, Integer> {
    List<Pic> findOneBySrc(String src);
}
