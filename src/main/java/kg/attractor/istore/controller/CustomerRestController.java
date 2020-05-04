package kg.attractor.istore.controller;

import kg.attractor.istore.dto.CustomerDTO;
import kg.attractor.istore.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public Page<CustomerDTO> getAll(Pageable pageable){
        return customerService.getAll(pageable);
    }
}
