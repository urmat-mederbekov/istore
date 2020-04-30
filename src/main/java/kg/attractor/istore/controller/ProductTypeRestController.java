package kg.attractor.istore.controller;

import kg.attractor.istore.dto.ProductTypeDTO;
import kg.attractor.istore.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-types")
@AllArgsConstructor
public class ProductTypeRestController {
    private final ProductTypeService productTypeService;

    @GetMapping
    public Page<ProductTypeDTO> getAll(Pageable pageable){
        return productTypeService.getAll(pageable);
    }
}
