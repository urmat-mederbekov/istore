package kg.attractor.istore.repository;

import kg.attractor.istore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByCustomerId(Integer id);
}
