package pl.edu.pollub.warsztaty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstRepository extends JpaRepository<Object, Long> {
}
