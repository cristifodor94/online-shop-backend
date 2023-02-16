package ro.msg.learning.shop.services.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.exceptions.NotFoundException;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.services.interfaces.IStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MostAbundantStrategy implements IStrategy {
    private final StockService stockService;

    @Override
    public List<Stock> createOrder(List<OrderDetail> products) {
        if (null == products) {
            throw new NotFoundException("Product list is empty");
        }
        List<Stock> stockList = stockService.findAll();
        List<Stock> result = new ArrayList<>();
        for (OrderDetail product : products) {
            List<Stock> stocks = stockList.stream().filter(e -> e.getProduct().equals(product.getProduct()))
                    .sorted(Comparator.comparingInt(Stock::getQuantity)).collect(Collectors.toList());
            Collections.reverse(stocks);
            if (null != stocks && !stocks.isEmpty() && stocks.get(0).getQuantity() >= product.getQuantity()) {
                result.add(stocks.get(0));
            }
            if (result.size() != products.size()) {
                throw new MissingStockException(ERROR_MESSAGE);
            }
            editStocksQuantity(result, products);

        } return result;
    }
    public void editStocksQuantity(List<Stock> stocks, List<OrderDetail> products) {
        for (Stock stock : stocks) {
            for (OrderDetail product : products) {
                if (stock.getProduct().equals(product.getProduct())) {
                    stockService.editQuantity(stock,product.getQuantity());
                    break;
                }
            }
        }
    }
}
