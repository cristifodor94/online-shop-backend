package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.services.OrderService;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
public class OrderController {
    private final OrderMapper orderMapper;
    private final OrderService orderService;
    private final ProductService productService;


    @PostMapping(value = "/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        List<OrderDetail> orderDetails = obtainOrderDetails(orderDTO);
        return new ResponseEntity<>(orderService.createOrder(orderMapper.orderDtoToOrder(orderDTO), orderDetails), HttpStatus.OK);
    }

    private List<OrderDetail> obtainOrderDetails(OrderDTO orderDTO) {
        return orderDTO.getOrderDetailDTOList().stream().map(e -> OrderDetail.builder()
                .product(productService.findProductById(e.getId()))
                .quantity(e.getQuantity()).build()).collect(Collectors.toList());
    }
}
