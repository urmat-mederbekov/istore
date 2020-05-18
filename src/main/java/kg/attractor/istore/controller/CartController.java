package kg.attractor.istore.controller;

import kg.attractor.istore.dto.CartDTO;
import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.model.Cart;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.Purchase;
import kg.attractor.istore.repository.CartRepo;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.ProductRepo;
import kg.attractor.istore.repository.PurchaseRepo;
import kg.attractor.istore.service.CustomerService;
import kg.attractor.istore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.PushBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import kg.attractor.istore.model.Constants;

@Controller
@AllArgsConstructor
class CartController {

    private final ProductService productService;
    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final PurchaseRepo purchaseRepo;

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false) List<Cart> cart,
                       Authentication authentication) {

        if(cartRepo.count() != cart.size()){
            cart.clear();
            Customer customer = customerRepo.findByEmail(authentication.getName()).get();

            List<Cart> carts = cartRepo.findAllByCustomerId(customer.getId());

            cart.addAll(carts);
        }

        if (cart != null) {
            model.addAttribute("cartItems", cart);
        }
        return "cart";
    }
    @GetMapping("/purchase")
    public String cart(Model model, Authentication authentication) {

        Customer customer = customerRepo.findByEmail(authentication.getName()).get();

        List<Purchase> purchases = purchaseRepo.findAllByCustomerId(customer.getId());

        System.out.println(purchases);

            model.addAttribute("purchases", purchases);

        return "purchase";
    }

    // это метод для асинхронных запросов
    // демонстрация добавления в "корзину" через параметр @SessionAttribute cart_model
    @PostMapping(path = "/cart", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean addToListRest(@RequestBody ProductDTO productDTO,
                                 @SessionAttribute(name = Constants.CART_ID, required = false) List<Cart> cart,
                                 Authentication authentication) {

        Customer customer = customerRepo.findByEmail(authentication.getName()).get();

        ProductDTO productData = productService.getById(productDTO.getId());
        if (cart != null) {
            Cart cart1 = Cart.builder()
                    .price(productData.getPrice()*productDTO.getQuantity())
                    .productName(productData.getName())
                    .customer(customer)
                    .quantity(productDTO.getQuantity())
                    .build();

            cartRepo.save(cart1);
            cart.add(cart1);

        }

        return true;
    }

    @PostMapping(path = "/purchase/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String purchase(@RequestBody CartDTO cartDTO){

        Cart cart = cartRepo.findById(cartDTO.getId()).get();

        Purchase purchase = Purchase.builder()
                .customer(cart.getCustomer())
                .price(cart.getPrice())
                .productName(cart.getProductName())
                .quantity(cart.getQuantity())
                .build();

        purchaseRepo.save(purchase);
        cartRepo.deleteById(cartDTO.getId());

        return "redirect:/purchase";
    }

    @PostMapping(path = "/cart/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String emptyCartById(@RequestBody CartDTO cartDTO) {

        cartRepo.deleteById(cartDTO.getId());
        return "redirect:/cart";
    }

    // в идеале это должно быть @DeleteMapping, но web-формы не поддерживают
    // запросы с методами отличными от get и post
    // по этому у нас адрес метода немного "странный" :)
    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session, Authentication authentication) {
        session.removeAttribute(Constants.CART_ID);

        return "redirect:/cart";
    }
}
