package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.OrderDetailKey;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {

}
