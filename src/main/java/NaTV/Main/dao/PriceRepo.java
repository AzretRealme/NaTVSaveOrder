package NaTV.Main.dao;

import NaTV.Main.models.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepo extends JpaRepository<Price, Long> {
    @Query(value = "SELECT * FROM prices WHERE CURRENT_TIMESTAMP BETWEEN start_date AND end_date" ,nativeQuery = true)
    List<Price>allActiveChannelsPrices();

    @Query(value = "select * from prices p where p.id_channels = ?1 and current_timestamp between start_date and end_date", nativeQuery = true)
    Price findByChannelAndDate(Long channelId);

}
