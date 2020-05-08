package kg.attractor.istore.repository;

import kg.attractor.istore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    Page<Product> findAllByTypeId(Integer type_id, Pageable pageable);

    Page<Product> findAllByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable);
}
