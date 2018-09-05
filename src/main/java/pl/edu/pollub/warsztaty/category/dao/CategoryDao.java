package pl.edu.pollub.warsztaty.category.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.category.domain.CategoryEntity;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
}
