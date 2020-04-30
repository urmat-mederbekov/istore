package kg.attractor.istore.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128)
    private String name;
    @Column(length = 500)
    private String description;
    @Column(length = 128)
    private String image;
    @Column(length = 128)
    private int quantity;
    @Column(length = 128)
    private float price;
    @ManyToOne
    @JoinColumn(name = "productType_id")
    private ProductType type;
}
