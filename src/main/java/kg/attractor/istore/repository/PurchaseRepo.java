package kg.attractor.istore.repository;

import kg.attractor.istore.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {
    Page<Purchase> findAllByCustomerId(Integer id, Pageable pageable);
    boolean existsByCustomerId(Integer id);
}
