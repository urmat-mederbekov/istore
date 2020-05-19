package kg.attractor.istore.dto;

import kg.attractor.istore.model.Purchase;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDTO {

    private int id;
    private String productName;
    private float quantity;
    private float price;
    private CustomerDTO customer;

    public static PurchaseDTO from(Purchase purchase){
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .productName(purchase.getProductName())
                .quantity(purchase.getQuantity())
                .price(purchase.getPrice())
                .customer(CustomerDTO.from(purchase.getCustomer()))
                .build();
    }
}
