package kg.attractor.istore.repository;

import kg.attractor.istore.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
}
