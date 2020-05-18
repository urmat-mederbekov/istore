package kg.attractor.istore.repository;

import kg.attractor.istore.model.Cart;
import kg.attractor.istore.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByCustomerId(Integer id);
}
