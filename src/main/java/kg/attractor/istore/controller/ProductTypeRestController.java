package kg.attractor.istore.controller;

import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.dto.ProductTypeDTO;
import kg.attractor.istore.repository.ProductRepo;
import kg.attractor.istore.service.ProductService;
import kg.attractor.istore.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-types")
@AllArgsConstructor
public class ProductTypeRestController {

    private final ProductTypeService productTypeService;
    private final ProductService productService;

    @GetMapping
    public List<ProductTypeDTO> getAll(Pageable pageable){
        return productTypeService.getAll(pageable).getContent();
    }

    @GetMapping("/{typeId}/products")
    public List<ProductDTO> getAllByTypeId(@PathVariable int typeId, Pageable pageable){
        return productService.getAllByTypeId(typeId, pageable).getContent();
    }

}
