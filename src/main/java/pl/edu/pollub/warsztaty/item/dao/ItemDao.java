package pl.edu.pollub.warsztaty.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.item.domain.Item;

@Repository
public interface ItemDao extends JpaRepository<Item, Long> {
}
