package pl.edu.pollub.warsztaty.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.dto.CategoryAmountAvgDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long> {

    @Query("select i from ItemEntity i " +
            "left join fetch i.bids " +
            "where i.id = :itemId")
    Optional<ItemEntity> findItemWithBids(@Param("itemId") Long itemId);

    @Query("select i from ItemEntity i " +
            "where size(i.bids) > :bidCount")
    List<ItemEntity> findByBidCountGreater(@Param("bidCount") Integer bidCount);


    @Query("select i from ItemEntity i " +
            "where :bidAmount < any (select b.amount from i.bids b)")
    List<ItemEntity> findAnyBidAmountGreaterThan(@Param("bidAmount") BigDecimal bidAmount);


    @Query("select i from ItemEntity i " +
            "where (select max(b.amount) from i.bids b) < :bidAmount")
    List<ItemEntity> findByBidMaxAmountLesserThan(@Param("bidAmount") BigDecimal bidAmount);

    @Query("select new pl.edu.pollub.warsztaty.item.dto.CategoryAmountAvgDto(i.category, avg(b.amount)) " +
            "from ItemEntity i " +
            "left join i.bids b " +
            "group by i.category")
    List<CategoryAmountAvgDto> findCategoryAmountAvg();

    @Query("select i from ItemEntity i " +
            "left join i.bids b on b.amount > :bidAmount")
    List<ItemEntity> findWithBidsWhichAmountGreaterThan(@Param("bidAmount") BigDecimal bidAmount);

}
