package ro.msg.learning.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double weight;
    private String imgUrl;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private ProductCategory productCategory;
}
