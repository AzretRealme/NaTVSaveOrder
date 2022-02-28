package NaTV.Main.dao;

import NaTV.Main.models.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    @Query(value = "SELECT * FROM channels WHERE id = (SELECT max(id) FROM channels)", nativeQuery = true)
    Channel findLastRow();

}
