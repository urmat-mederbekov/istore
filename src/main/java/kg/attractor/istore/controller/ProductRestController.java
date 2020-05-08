package kg.attractor.istore.controller;

import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll(Pageable pageable){
        return productService.getAll(pageable).getContent();
    }

    @GetMapping("/search={text}")
    public List<ProductDTO> search(@PathVariable String text, Pageable pageable){
        System.out.println(text);
        return productService.search(text, text, pageable).getContent();
    }
}
