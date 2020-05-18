package kg.attractor.istore.dto;

import kg.attractor.istore.model.Cart;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartDTO {

    private int id;
    private String productName;
    private float quantity;
    private float price;
    private CustomerDTO customer;

    public static CartDTO from(Cart cart){
        return CartDTO.builder()
                .id(cart.getId())
                .productName(cart.getProductName())
                .quantity(cart.getQuantity())
                .price(cart.getPrice())
                .customer(CustomerDTO.from(cart.getCustomer()))
                .build();
    }
}
