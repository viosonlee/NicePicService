package lee.vioson.nicepicservice.repository;

import lee.vioson.nicepicservice.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    List<Type> findOneByHref(String href);
}
