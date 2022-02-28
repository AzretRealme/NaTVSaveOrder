package NaTV.Main.dao;

import NaTV.Main.models.entity.OrderDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDayRepo extends JpaRepository<OrderDay, Long> {
    @Query(value = "select * from order_days od where od.id_order_detail = ?1", nativeQuery = true)
    List<OrderDay> findByOrderDetailId(Long id);
}
