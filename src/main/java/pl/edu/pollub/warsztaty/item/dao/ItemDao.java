package pl.edu.pollub.warsztaty.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

@Repository
public interface ItemDao extends JpaRepository<ItemEntity, Long> {
}
