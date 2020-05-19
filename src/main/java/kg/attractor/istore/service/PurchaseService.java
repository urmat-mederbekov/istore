package kg.attractor.istore.service;

import kg.attractor.istore.dto.CartDTO;
import kg.attractor.istore.dto.PurchaseDTO;
import kg.attractor.istore.exception.CustomerNotFoundException;
import kg.attractor.istore.exception.ProductNotFoundException;
import kg.attractor.istore.model.Cart;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.Purchase;
import kg.attractor.istore.repository.CartRepo;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.PurchaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;

    public Page<PurchaseDTO> getPurchases(Authentication authentication, Pageable pageable){

        Customer customer = customerRepo.findByEmail(authentication.getName()).orElseThrow(CustomerNotFoundException::new);

        return purchaseRepo.findAllByCustomerId(customer.getId(), pageable).map(PurchaseDTO::from);

    }

    public void purchase(CartDTO cartDTO){

        //find the element to purchase
        Cart cart = cartRepo.findById(cartDTO.getId()).orElseThrow(ProductNotFoundException::new);

        Purchase purchase = Purchase.builder()
                .customer(cart.getCustomer())
                .price(cart.getPrice())
                .productName(cart.getProductName())
                .quantity(cart.getQuantity())
                .build();

        //save to db
        purchaseRepo.save(purchase);
        //delete from cart in db
        cartRepo.deleteById(cartDTO.getId());
    }
}
