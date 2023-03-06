package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    public Order orderDtoToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .created(orderDTO.getCreated())
                .address(Address.builder()
                        .city(orderDTO.getCity())
                        .county(orderDTO.getCounty())
                        .street(orderDTO.getStreet())
                        .country(orderDTO.getCountry())
                        .build())
                .build();
    }

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .created(order.getCreated())
                .city(order.getAddress().getCity())
                .county(order.getAddress().getCounty())
                .country(order.getAddress().getCountry())
                .street(order.getAddress().getStreet())
                .build();
    }
}
