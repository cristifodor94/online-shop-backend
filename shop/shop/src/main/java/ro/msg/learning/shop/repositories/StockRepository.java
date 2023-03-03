package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findAllByLocation(Location location);


    @Query("SELECT s FROM Stock s WHERE s.product.id = :productId and s.quantity >= :quantity ORDER by s.quantity DESC LIMIT 1")
    List<Stock> findLocationByProductIAndQuantityDesc(@Param("productId") Integer product, @Param("quantity") Integer quantity);
}
