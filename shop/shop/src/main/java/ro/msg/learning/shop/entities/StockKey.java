package ro.msg.learning.shop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockKey implements Serializable {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "location_Id")
    private Integer locationId;
}
