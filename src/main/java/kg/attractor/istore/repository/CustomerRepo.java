package kg.attractor.istore.repository;

import kg.attractor.istore.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}

