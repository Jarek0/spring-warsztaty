package pl.edu.pollub.warsztaty.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pollub.warsztaty.item.domain.Item;

public interface ItemDao extends JpaRepository<Item, Long> {
}
