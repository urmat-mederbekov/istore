package kg.attractor.istore.repository;

import kg.attractor.istore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
