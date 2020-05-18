package kg.attractor.istore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Table(name="carts")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String productName;

//    @NotBlank
//    @Size(min = 1, max = 128)
//    @Column(length = 128)
//    private LocalDateTime dateTime;

    @PositiveOrZero
    @Column(length = 128)
    private float quantity;

    @PositiveOrZero
    @Column(length = 128)
    @NotNull
    private float price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
