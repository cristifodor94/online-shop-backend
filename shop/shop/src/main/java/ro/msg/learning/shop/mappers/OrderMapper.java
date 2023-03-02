package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;

@Component
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
}
