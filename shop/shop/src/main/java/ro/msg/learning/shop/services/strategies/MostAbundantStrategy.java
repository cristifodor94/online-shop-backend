package ro.msg.learning.shop.services.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.services.interfaces.IStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements IStrategy {
    private final StockService stockService;

    @Override
    public List<Stock> createOrder(List<OrderDetail> products) {
        if (null == products) {
            throw new MissingStockException("Product list is empty");
        }
        List<Stock> result = new ArrayList<>();
        for (OrderDetail orderDetail : products) {
            Optional<Stock> stocks = stockService.findMostAbundantByProductId(orderDetail.getProduct().getId(), orderDetail.getQuantity());
            if (null != stocks && !stocks.isEmpty() && stocks.get().getQuantity() >= orderDetail.getQuantity()) {
                result.add(stocks.get());
            }
        }
        if (result.size() != products.size()) {
            throw new MissingStockException(ERROR_MESSAGE);
        }
        editStocksQuantity(result, products);
        return result;
    }

    private void editStocksQuantity(List<Stock> stocks, List<OrderDetail> products) {
        for (Stock stock : stocks) {
            for (OrderDetail orderDetail : products) {
                if (stock.getProduct().equals(orderDetail.getProduct())) {
                    stockService.editQuantity(stock, orderDetail.getQuantity());
                    break;
                }
            }
        }
    }
}
