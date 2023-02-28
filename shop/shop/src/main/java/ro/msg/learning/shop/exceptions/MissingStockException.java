package ro.msg.learning.shop.exceptions;

public class MissingStockException extends RuntimeException {
    public MissingStockException(String message) {
        super(message);
    }
}
