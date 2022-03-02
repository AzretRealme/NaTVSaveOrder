package NaTV.Main.dao;

import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    @Query(value = "SELECT * FROM channels WHERE id = (SELECT max(id) FROM channels)", nativeQuery = true)
    Channel findLastRow();

    @Query(value = "SELECT * FROM channels ds WHERE ds.id_channels = ?1 AND CURRENT_TIMESTAMP BETWEEN start_date AND end_date", nativeQuery = true)
    List<Channel> findChannelByIdForDiscount(Long id);

}
