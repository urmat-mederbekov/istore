package kg.attractor.istore.service;

import kg.attractor.istore.repository.CartRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepo cartRepo;

}
