package kg.attractor.istore.controller;

import kg.attractor.istore.exception.ResourceNotFoundException;
import kg.attractor.istore.model.CustomerRegisterForm;
import kg.attractor.istore.service.CustomerService;
import kg.attractor.istore.service.ProductService;
import kg.attractor.istore.service.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private final ProductService productService;
    private final PropertiesService propertiesService;

    private final CustomerService customerService;

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        System.out.println(12);
        var user = customerService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerRegisterForm());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid CustomerRegisterForm customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        attributes.addFlashAttribute("dto", customerRequestDto);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        customerService.register(customerRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        var products = productService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();

        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }

    @GetMapping("/iphones")
    public String getIphones(){
        return "iphone";
    }

    @GetMapping("/macs")
    public String getMacs(){
        return "mac";
    }

    @GetMapping("/airpods")
    public String getAirpods(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        var products = productService.getAllByTypeId(4, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "airpods";
    }

    @GetMapping("/ipads")
    public String getIpads(){
        return "ipad";
    }

    @GetMapping("/apple-watches")
    public String getAppleWatches(){
        return "apple_watch";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }
}