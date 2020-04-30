package kg.attractor.istore.service;

import kg.attractor.istore.dto.ProductTypeDTO;
import kg.attractor.istore.repository.ProductTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepo productTypeRepo;

    public Page<ProductTypeDTO> getAll(Pageable pageable){
        return productTypeRepo.findAll(pageable).map(ProductTypeDTO::from);
    }
}
