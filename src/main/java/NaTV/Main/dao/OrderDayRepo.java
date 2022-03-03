package NaTV.Main.dao;

import NaTV.Main.models.entity.OrderDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDayRepo extends JpaRepository<OrderDay, Long> {

}
