package pl.edu.pollub.warsztaty.bid.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;

import java.math.BigDecimal;
import java.util.Optional;

public interface BidDao extends JpaRepository<BidEntity, Long> {

    Optional<BidEntity> findById(Long id);

    @Query("select max(b.amount) from BidEntity b where b.item.id = :itemId")
    Optional<BigDecimal> findMaxBidForItem(@Param("itemId") Long itemId);
}
