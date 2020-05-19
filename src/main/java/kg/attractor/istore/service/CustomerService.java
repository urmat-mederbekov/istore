package kg.attractor.istore.service;

import kg.attractor.istore.dto.CustomerDTO;
import kg.attractor.istore.exception.CustomerAlreadyRegisteredException;
import kg.attractor.istore.exception.CustomerNotFoundException;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.CustomerRegisterForm;
import kg.attractor.istore.model.NewPassword;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.NewPasswordRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final PasswordEncoder encoder;
    private final NewPasswordRepo newPasswordRepo;

    public Page<CustomerDTO> getAll(Pageable pageable){
        return customerRepo.findAll(pageable).map(CustomerDTO::from);
    }

    public CustomerDTO register(CustomerRegisterForm form) {
        if (customerRepo.existsByEmail(form.getEmail())) {
            throw new CustomerAlreadyRegisteredException();
        }

        var user = Customer.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();

        customerRepo.save(user);

        return CustomerDTO.from(user);
    }

    public void resetPassword(String token, String newPassword){

        NewPassword resetPassword = newPasswordRepo.findByToken(token).get();
        Customer customer = customerRepo.findById(resetPassword.getCustomer().getId()).get();
        customer.setPassword(new BCryptPasswordEncoder().encode(newPassword));

        customerRepo.save(customer);
    }

    public CustomerDTO getByEmail(String email) {

        var user = customerRepo.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);

        return CustomerDTO.from(user);
    }

    public boolean existsByEmail(String email){

        return customerRepo.existsByEmail(email);
    }
}
