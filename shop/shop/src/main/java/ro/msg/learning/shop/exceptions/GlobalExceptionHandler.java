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
    @ExceptionHandler({MissingStockException.class})
    public ResponseEntity<String> handleOrderNotCreatedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Order not created because of missing stock", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleProductNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                "Product not found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
