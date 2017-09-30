package lee.vioson.nicepicservice.repository;

import lee.vioson.nicepicservice.models.ListData;
import lee.vioson.nicepicservice.models.Pic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListDataRepository extends JpaRepository<ListData, Integer> {
    List<ListData> findOneBySrc(String src);
}
