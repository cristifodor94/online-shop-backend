package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entities.OrderDetail;

@Component
@NoArgsConstructor
public class OrderDetailMapper {
    public OrderDetailDTO orderDetailToDto(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .quantity(orderDetail.getQuantity())
                .build();
    }
    public OrderDetail orderDtoToOrder(OrderDetailDTO orderDetailDTO) {
        return OrderDetail.builder()
                .quantity(orderDetailDTO.getQuantity())
                .build();
    }
}
