package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.NotFoundException;

import java.util.List;

public interface IStrategy {

    public static final String ERROR_MESSAGE="There is no sufficient stock to create the order!";
    List<Stock> createOrder(List<OrderDetail> products) throws NotFoundException;
}
