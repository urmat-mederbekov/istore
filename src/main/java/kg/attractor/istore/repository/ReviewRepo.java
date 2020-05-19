package kg.attractor.istore.repository;

import kg.attractor.istore.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

    boolean existsAllByProductId(Integer id);
    List<Review> findAllByProductId(Integer id);
}
