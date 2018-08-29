package pl.edu.pollub.warsztaty.bid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;

import java.math.BigDecimal;

public interface BidDao extends JpaRepository<BidEntity, Long> {

    @Query("select max(b.amount) from BidEntity b where b.item.id = :itemId")
    BigDecimal findMaxBidForItem(@Param("itemId") Long itemId);
}
