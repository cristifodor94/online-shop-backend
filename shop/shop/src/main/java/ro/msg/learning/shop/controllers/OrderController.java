package ro.msg.learning.shop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.mappers.OrderDetailMapper;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.services.OrderService;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor


public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final OrderDetailMapper orderDetailMapper;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order orderToCreate = orderService.createOrder(orderMapper.orderDtoToOrder(orderDTO), orderDetailMapper.orderDetailDtoToOrderDetail(orderDTO.getOrderDetails()));
        orderDTO = orderMapper.orderToOrderDTO(orderToCreate);
        List<OrderDetailDTO> orderDetailDTOS = orderDetailMapper.orderDetailToOrderDetailDTO(orderToCreate.getOrderDetails());
        orderDTO.setOrderDetails(orderDetailDTOS);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
