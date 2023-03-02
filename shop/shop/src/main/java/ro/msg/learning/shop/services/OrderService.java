package ro.msg.learning.shop.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.StrategyConfiguration;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

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
        try {
            List<Stock> stockList = strategyConfiguration.findStrategy().createOrder(orderDetails);
            List<Location> locationList = stockList.stream().map(Stock::getLocation).collect(Collectors.toList());
            order.setShippedFrom(locationList.get(0));
            Customer customer = customerService.findById(1);
            order.setCustomer(customer);
            orderRepository.save(order);
            orderDetails.forEach(e -> e.setOrder(order));
            orderDetails.forEach(orderDetailService::save);
            return order;
        } catch (MissingStockException e) {
            throw new OrderNotCreatedException(e.getMessage() + " | " + "Could not create the order!");
        }
    }

}
