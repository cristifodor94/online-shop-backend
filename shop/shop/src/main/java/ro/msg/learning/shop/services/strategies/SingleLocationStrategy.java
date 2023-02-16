package ro.msg.learning.shop.services.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.MissingStockException;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.services.interfaces.IStrategy;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SingleLocationStrategy implements IStrategy {
    private final LocationService locationService;
    private final StockService stockService;

    @Override
    public List<Stock> createOrder(List<OrderDetail> products) {
        List<Location> locations = locationService.findAll();
        for (Location location : locations) {
            List<Stock> stocks = stockService.findAllByLocation(location);
            int count = checkStocksAvailability(products, stocks);
                if (count == products.size()) {
                    editStocksQuantity(stocks, products);
                    return Arrays.asList(stocks.get(0));
                }

        } throw new MissingStockException(ERROR_MESSAGE);
    }
    private int checkStocksAvailability(List<OrderDetail> products, List<Stock> stocks) {
        int count = 0;
        for (Stock currentStock : stocks) {
            for (OrderDetail product : products){
                if (currentStock.getProduct().equals(product.getProduct()) && currentStock.getQuantity() >= product.getQuantity()) {
                    count ++;
                    break;
                }
            }
            } return count;
    }

    public void editStocksQuantity(List<Stock> stocks, List<OrderDetail> products) {
        for (Stock stock : stocks) {
            for (OrderDetail product : products) {
                if (stock.getProduct().equals(product.getProduct())) {
                    stockService.editQuantity(stock, product.getQuantity());
                    break;
                }
            }
        }
    }
}

