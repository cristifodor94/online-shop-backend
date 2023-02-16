package ro.msg.learning.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "product_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime created;
    private String country;
    private String city;
    private String county;
    private String street;

    @JoinColumn(name = "shipped_from_id")
    @ManyToOne
    private Location shippedFrom;
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;
}
