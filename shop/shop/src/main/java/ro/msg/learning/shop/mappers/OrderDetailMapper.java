package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class OrderDetailMapper {

    private final ProductMapper productMapper;

    public OrderDetailDTO orderDetailToDto(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .id(orderDetail.getId())
                .quantity(orderDetail.getQuantity())
                .product(orderDetail.getProduct())
                .build();
    }

    public List<OrderDetail> orderDtoToOrderDetail(List<OrderDetailDTO> orderDetailDTO) {
        return orderDetailDTO.stream()
                .map(dto -> OrderDetail.builder()
                        .product(Product.builder().id(dto.getProduct().getId()).build())
                        .quantity(dto.getQuantity()).build()).collect(Collectors.toList());
    }
}
