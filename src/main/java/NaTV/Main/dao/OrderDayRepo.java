package NaTV.Main.dao;

import NaTV.Main.models.entity.OrderDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDayRepo extends JpaRepository<OrderDay, Long> {
}
