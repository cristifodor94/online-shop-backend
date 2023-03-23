package ro.msg.learning.shop.services;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configurations.StrategyConfiguration;
import ro.msg.learning.shop.entities.Customer;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.services.interfaces.EmailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderDetailService orderDetailService;
    private final CustomerService customerService;
    private final StrategyConfiguration strategyConfiguration;
    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @Transactional
    public Order createOrder(Order order, List<OrderDetail> orderDetails) throws MessagingException {
        List<Stock> stockList = strategyConfiguration.findStrategy().createOrder(orderDetails);
        order.setShippedFrom(stockList.get(0).getLocation());
        Customer customer = customerService.findById(1);
        order.setCustomer(customer);
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        orderDetails.forEach(e -> e.setOrder(order));
        orderDetails.forEach(orderDetailService::save);

        //create templateModel for thymeleaf template
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("orderId", order.getId());
        templateModel.put("address", order.getAddress());
        templateModel.put("orderDetails", order.getOrderDetails());
        emailService.sendMessageUsingThymeleafTemplate(
                order.getCustomer().getEmailAddress(), "Order confirmation", templateModel);
        return order;
    }
}
