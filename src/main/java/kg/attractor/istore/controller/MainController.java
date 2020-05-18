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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;

@Controller
@AllArgsConstructor
public class MainController {

    private final ProductService productService;
    private final PropertiesService propertiesService;
    private final CustomerService customerService;

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
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

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder, HttpSession session) {
        var products = productService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();

        var map = new HashMap<String, Object>();

        session.getAttributeNames()
                .asIterator()
                .forEachRemaining(key -> map.put(key, session.getAttribute(key).toString()));

        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }

    @GetMapping("/airpods")
    public String getAirpods(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        model.addAttribute("product", "Airpods");
        var products = productService.getAllByTypeId(4, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/iphones")
    public String getIphones(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        model.addAttribute("product", "Iphone");
        var products = productService.getAllByTypeId(1, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/macs")
    public String getMacs(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        model.addAttribute("product", "Mac");
        var products = productService.getAllByTypeId(3, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/ipads")
    public String getIpads(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        model.addAttribute("product", "Ipad");
        var products = productService.getAllByTypeId(2, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/apple-watches")
    public String getAppleWatches(Model model, Pageable pageable, HttpServletRequest uriBuilder){
        model.addAttribute("product", "Apple Watch");
        var products = productService.getAllByTypeId(5, pageable);
        var uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
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

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}