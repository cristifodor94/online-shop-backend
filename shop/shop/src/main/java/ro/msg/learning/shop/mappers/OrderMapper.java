package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;

@Component
public class OrderMapper {

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .created(order.getCreated())
                .street(order.getStreet())
                .city(order.getCity())
                .country(order.getCountry())
                .county(order.getCounty())
                .build();
    }

    public Order orderDtoToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .created(orderDTO.getCreated())
                .street(orderDTO.getStreet())
                .city(orderDTO.getCity())
                .country(orderDTO.getCountry())
                .county(orderDTO.getCounty())
                .build();
    }

}
