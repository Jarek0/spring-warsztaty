package pl.edu.pollub.warsztaty.userAccount.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import java.util.Optional;

public interface UserAccountDao extends JpaRepository<UserAccountEntity, Long>, CustomizedUserAccountDao {

    Optional<UserAccountEntity> findByLogin(String login);

}
