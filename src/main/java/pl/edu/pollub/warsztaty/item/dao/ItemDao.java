package pl.edu.pollub.warsztaty.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import java.util.Optional;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long>, JpaSpecificationExecutor<ItemEntity> {

    Optional<ItemEntity> findById(Long id);

    @Query("select i from ItemEntity i left join fetch i.bids where i.id = :id")
    Optional<ItemEntity> findByIdFetchBids(@Param("id") Long id);

    Optional<ItemEntity> findByNameAndIdNot(String name, Long id);

    Long countByCategoryAndIdNot(String category, Long id);
}
