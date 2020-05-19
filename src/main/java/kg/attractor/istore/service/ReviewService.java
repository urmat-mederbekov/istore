package kg.attractor.istore.service;

import kg.attractor.istore.dto.ReviewDTO;
import kg.attractor.istore.exception.CustomerNotFoundException;
import kg.attractor.istore.exception.ProductNotFoundException;
import kg.attractor.istore.model.Customer;
import kg.attractor.istore.model.Product;
import kg.attractor.istore.model.Review;
import kg.attractor.istore.repository.CustomerRepo;
import kg.attractor.istore.repository.ProductRepo;
import kg.attractor.istore.repository.PurchaseRepo;
import kg.attractor.istore.repository.ReviewRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepo reviewRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;
    private final PurchaseRepo purchaseRepo;

    public List<ReviewDTO> getReviewsByProductId(Integer id){
            List<Review> reviews = reviewRepo.findAllByProductId(id);
            List<ReviewDTO> reviewDTOS = reviews.stream().map(ReviewDTO::from).collect(Collectors.toList());

        return reviewDTOS;
    }

    public void review(Integer id, String text, Authentication authentication){

        Product product = productRepo.findById(id).orElseThrow(ProductNotFoundException::new);
        Customer customer = customerRepo.findByEmail(authentication.getName()).orElseThrow(CustomerNotFoundException::new);

        if(purchaseRepo.existsByCustomerId(customer.getId())){
            Review review = Review.builder().customer(customer)
                    .product(product)
                    .text(text)
                    .build();
            reviewRepo.save(review);
        }
    }
}
