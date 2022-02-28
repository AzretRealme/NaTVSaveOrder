package NaTV.Main.dao;

import NaTV.Main.models.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {

}




//    @Query(value = "SELECT * FROM discounts", nativeQuery = true)
//    List<Order> allActiveOrders();