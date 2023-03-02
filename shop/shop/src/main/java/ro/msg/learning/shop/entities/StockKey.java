package ro.msg.learning.shop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class StockKey implements Serializable {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "location_Id")
    private Integer locationId;
}
