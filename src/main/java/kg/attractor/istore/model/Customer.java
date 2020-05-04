package kg.attractor.istore.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128)
    private String username;
    @Column(length = 128)
    private String email;
    @Column(length = 128)
    private String firstname;
    @Column(length = 128)
    private String lastname;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
//    @OrderBy("name ASC")
//    private List<Product> purchaseList;
}
