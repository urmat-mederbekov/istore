package kg.attractor.istore.frontendController;

import kg.attractor.istore.dto.CartDTO;
import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.dto.ReviewDTO;
import kg.attractor.istore.exception.ResourceNotFoundException;
import kg.attractor.istore.model.Constants;
import kg.attractor.istore.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
class CartController {

    private final CartService cartService;
    private final PurchaseService purchaseService;
    private final PropertiesService propertiesService;
    private final ProductService productService;
    private final ReviewService reviewService;

    @GetMapping("/products/{id}/reviews")
    public String reviews(@PathVariable String id, Model model){

        List<ReviewDTO> reviewDTOS = reviewService.getReviewsByProductId(Integer.parseInt(id));

        if(!reviewDTOS.isEmpty()){
            model.addAttribute("items", reviewDTOS);
        }

        ProductDTO product = productService.getById(Integer.parseInt(id));
        model.addAttribute("product", product);

        return "review";
    }

    @PostMapping("/reviews/{id}")
    public String review(@PathVariable String id, @RequestParam String text, Authentication authentication){

        reviewService.review(Integer.parseInt(id), text, authentication);

        return "redirect:/products/{id}/reviews";
    }

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false) List<CartDTO> cart,
                       Authentication authentication) {

        var carts = cartService.getCart(cart, authentication);
        model.addAttribute("cartItems", carts);

        return "cart";
    }

    @GetMapping("/purchases")
    public String purchases(Model model, Authentication authentication, Pageable pageable, HttpServletRequest uriBuilder) {

        var purchases = purchaseService.getPurchases(authentication, pageable);
        var uri = uriBuilder.getRequestURI();
        propertiesService.setDefaultPageSize(10);
        PropertiesService.constructPageable(purchases, propertiesService.getDefaultPageSize(), model, uri);

        return "purchase";
    }

    // это метод для асинхронных запросов
    // демонстрация добавления в "корзину" через параметр @SessionAttribute cart_model
    @PostMapping(path = "/cart", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean addToCart(@RequestBody ProductDTO productDTO,
                                 @SessionAttribute(name = Constants.CART_ID, required = false) List<CartDTO> cart,
                                 Authentication authentication) {

        cartService.addToCart(productDTO, cart, authentication);

        return true;
    }

    @PostMapping(path = "/purchase/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String purchase(@RequestBody CartDTO cartDTO){

        purchaseService.purchase(cartDTO);

        return "redirect:/purchases";
    }

    @PostMapping(path = "/cart/id", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String emptyCartById(@RequestBody CartDTO cartDTO) {

        cartService.deleteCart(cartDTO.getId());

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

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {

        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}
