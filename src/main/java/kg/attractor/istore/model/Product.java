package kg.attractor.istore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String name;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 500)
    private String description;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String image;

    @PositiveOrZero
    @Column(length = 128)
    private int quantity;

    @PositiveOrZero
    @Column(length = 128)
    private float price;
    @ManyToOne
    @JoinColumn(name = "productType_id")
    private ProductType type;
    @ManyToMany(mappedBy = "products")
    private List<Customer> customers;
}
