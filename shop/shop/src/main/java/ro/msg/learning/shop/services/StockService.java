package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public List<Stock> findAllByLocation(Location location) {
        return stockRepository.findAllByLocation(location);
    }

    public void deleteAll() {
        stockRepository.deleteAll();
    }

    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    public void editQuantity(Stock stock, Integer quantity) {
        Integer newQuantity = stock.getQuantity() - quantity;
        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
    }

    public List<Stock> findTopByProductIdOrderByQuantityDesc(Integer productId, Integer quantity) {
        return stockRepository.findTopByProductIdOrderByQuantityDesc(productId, quantity);
    }
}
