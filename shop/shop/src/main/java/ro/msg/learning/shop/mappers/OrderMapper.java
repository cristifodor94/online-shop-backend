package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;

@Component
@NoArgsConstructor
public class OrderMapper {
    private OrderDetailMapper orderDetailMapper;
public OrderDTO orderToOrderDTO(Order order) {
    return OrderDTO.builder()
            .id(order.getId())
            .build();
}

public Order orderDtoToOrder(OrderDTO orderDTO) {
    return Order.builder()
            .id(orderDTO.getId())
            .build();
}

}
