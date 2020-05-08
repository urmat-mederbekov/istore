package kg.attractor.istore.service;

import kg.attractor.istore.dto.ProductDTO;
import kg.attractor.istore.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public Page<ProductDTO> getAll(Pageable pageable){
        return productRepo.findAll(pageable).map(ProductDTO::from);
    }

    public Page<ProductDTO> getAllByTypeId(int id, Pageable pageable){
        return productRepo.findAllByTypeId(id, pageable).map(ProductDTO::from);
    }

    public Page<ProductDTO> search(String name, String description, Pageable pageable){
        return productRepo.findAllByNameContainingOrDescriptionContaining(name, description, pageable)
                .map(ProductDTO::from);
    }
}
