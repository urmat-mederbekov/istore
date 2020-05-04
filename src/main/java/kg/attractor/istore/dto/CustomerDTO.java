package kg.attractor.istore.dto;

import kg.attractor.istore.model.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    public static CustomerDTO from(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .build();
    }

    private Integer id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
