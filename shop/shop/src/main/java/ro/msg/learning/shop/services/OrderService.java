package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.StrategyConfiguration;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.OrderRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderDetailService orderDetailService;
    private final CustomerService customerService;
    private final StrategyConfiguration strategyConfiguration;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Order order, List<OrderDetail> orderDetails) {
        List<Stock> stockList = strategyConfiguration.findStrategy().createOrder(orderDetails);
        order.setShippedFrom(stockList.get(0).getLocation());
        Customer customer = customerService.findById(15);
        order.setCustomer(customer);
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        orderDetails.forEach(e -> e.setOrder(order));
        orderDetails.forEach(orderDetailService::save);
        return order;
    }
}
