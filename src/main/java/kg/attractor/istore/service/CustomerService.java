package kg.attractor.istore.service;

import kg.attractor.istore.dto.CustomerDTO;
import kg.attractor.istore.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;

    public Page<CustomerDTO> getAll(Pageable pageable){
        return customerRepo.findAll(pageable).map(CustomerDTO::from);
    }
}
