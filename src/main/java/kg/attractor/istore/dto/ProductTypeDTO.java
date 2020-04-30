package kg.attractor.istore.dto;

import kg.attractor.istore.model.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductTypeDTO {
    public static ProductTypeDTO from(ProductType productType){
        return ProductTypeDTO.builder()
                .id(productType.getId())
                .name(productType.getName())
                .build();
    }

    private Integer id;
    private String name;
}
