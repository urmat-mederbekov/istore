package kg.attractor.istore.service;

import kg.attractor.istore.dto.CartDTO;
import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.exception.CustomerNotFoundException;
import kg.attractor.istore.exception.ProductNotFoundException;
import kg.attractor.istore.model.Cart;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.Product;
import kg.attractor.istore.repository.CartRepo;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    public List<CartDTO> getCart(List<CartDTO> cart, Authentication authentication){

        if(cartRepo.count() != cart.size()){
        // if condition is true clear session cart
            cart.clear();
            // find current customer
            Customer customer = customerRepo.findByEmail(authentication.getName()).orElseThrow(CustomerNotFoundException::new);
            // find all the products in the cart of current customer
            List<Cart> carts = cartRepo.findAllByCustomerId(customer.getId());
            // Cart -> CartDTO
            List<CartDTO> cartDTOS = carts.stream().map(CartDTO::from).collect(Collectors.toList());
            // save to session cart
            cart.addAll(cartDTOS);

            return cart;
        }
        return cart;
    }

    public void addToCart(ProductDTO productDTO, List<CartDTO> sessionCart, Authentication authentication){

        Customer customer = customerRepo.findByEmail(authentication.getName()).get();

        Product product = productRepo.findById(productDTO.getId())
                .orElseThrow(ProductNotFoundException::new);

        if (sessionCart != null) {

            Cart cart = Cart.builder()
                    .price(product.getPrice()*productDTO.getQuantity())
                    .productName(product.getName())
                    .customer(customer)
                    .quantity(productDTO.getQuantity())
                    .build();

            cartRepo.save(cart);

            sessionCart.add(CartDTO.from(cart));
        }
    }

    public void deleteCart(Integer id){
        cartRepo.deleteById(id);
    }

}
