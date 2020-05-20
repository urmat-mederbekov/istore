package kg.attractor.istore.service;

import kg.attractor.istore.exception.CustomerNotFoundException;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.NewPassword;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.NewPasswordRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class NewPasswordService {

    private final NewPasswordRepo newPasswordRepo;
    private final CustomerRepo customerRepo;
    private final EmailServiceImpl emailService;

    public void createToken(String email){

        Customer customer = customerRepo.findByEmail(email).orElseThrow(CustomerNotFoundException::new);
        NewPassword token = NewPassword.builder()
                .customer(customer)
                .token(UUID.randomUUID().toString())
                .build();

        newPasswordRepo.deleteAll();
        newPasswordRepo.save(token);

        emailService.sendSimpleMessage(customer.getEmail(), "Istore | Password Recovery",
                "Here is your token: " + token.getToken());
    }

    public boolean existsByToken(String token){
        return newPasswordRepo.existsByToken(token);
    }
}
