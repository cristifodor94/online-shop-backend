package ro.msg.learning.shop.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@Table(name = "order_detail")
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
}
