package ro.msg.learning.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.utils.Address;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name = "location")
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @ManyToMany(mappedBy = "shippedFrom", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@Fetch(value = FetchMode.SELECT)
    private Set<Order> orders;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Revenue> revenues;


}
