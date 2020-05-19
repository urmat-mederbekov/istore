package kg.attractor.istore.frontendController;

import kg.attractor.istore.exception.ResourceNotFoundException;
import kg.attractor.istore.service.ProductService;
import kg.attractor.istore.service.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final PropertiesService propertiesService;

    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {

        var products = productService.getAll(pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "index";
    }

    @GetMapping("/airpods")
    public String getAirpods(Model model, Pageable pageable, HttpServletRequest uriBuilder){

        model.addAttribute("product", "Airpods");
        var products = productService.getAllByTypeId(4, pageable);
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/iphones")
    public String getIphones(Model model, Pageable pageable, HttpServletRequest uriBuilder){

        model.addAttribute("product", "Iphone");
        var products = productService.getAllByTypeId(1, pageable);
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/macs")
    public String getMacs(Model model, Pageable pageable, HttpServletRequest uriBuilder){

        model.addAttribute("product", "Mac");
        var products = productService.getAllByTypeId(3, pageable);
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/ipads")
    public String getIpads(Model model, Pageable pageable, HttpServletRequest uriBuilder){

        model.addAttribute("product", "Ipad");
        var products = productService.getAllByTypeId(2, pageable);
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @GetMapping("/apple-watches")
    public String getAppleWatches(Model model, Pageable pageable, HttpServletRequest uriBuilder){

        model.addAttribute("product", "Apple Watch");
        var products = productService.getAllByTypeId(5, pageable);
        var uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);

        return "product";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {

        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }
}
