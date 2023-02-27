package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
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
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        List<OrderDetail> orderDetails = orderDetailMapper.orderDtoToOrderDetail(orderDTO.getOrderDetails());
        return new ResponseEntity<>(orderService.createOrder(orderMapper.orderDtoToOrder(orderDTO), orderDetails), HttpStatus.OK);
    }
}
