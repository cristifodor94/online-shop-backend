package ro.msg.learning.shop.exceptions;

public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException(String errorMessage) {
        super(errorMessage);
    }
}
