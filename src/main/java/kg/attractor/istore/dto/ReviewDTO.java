package kg.attractor.istore.dto;

import kg.attractor.istore.model.Review;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDTO {

    private int id;
    private String text;
    private ProductDTO product;
    private CustomerDTO customer;

    public static ReviewDTO from(Review review){
        return ReviewDTO.builder()
                .id(review.getId())
                .product(ProductDTO.from(review.getProduct()))
                .customer(CustomerDTO.from(review.getCustomer()))
                .text(review.getText())
                .build();
    }
}
