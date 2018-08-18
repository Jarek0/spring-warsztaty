package pl.edu.pollub.warsztaty.userAccount.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import java.util.Optional;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccountEntity, Long> {

    Optional<UserAccountEntity> findByLogin(String login);

}
