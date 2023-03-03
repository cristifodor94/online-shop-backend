package ro.msg.learning.shop.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({OrderNotCreatedException.class})
    public ResponseEntity<Object> handleOrderNotCreatedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Order not created because of missing stock", new HttpHeaders(), HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleProductNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Product not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
