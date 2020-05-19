package kg.attractor.istore.repository;

import kg.attractor.istore.model.NewPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewPasswordRepo extends JpaRepository<NewPassword, Integer> {

    boolean existsByToken(String token);

    void deleteAll();

    Optional<NewPassword> findByToken(String token);
}
