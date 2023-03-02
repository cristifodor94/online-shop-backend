package ro.msg.learning.shop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Stock")
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @EmbeddedId
    private StockKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @MapsId("locationId")
    @JoinColumn(name = "location_id")
    private Location location;
    private Integer quantity;

}
