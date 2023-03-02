package ro.msg.learning.shop.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailKey implements Serializable {
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "order_id")
    private Integer orderId;
}
