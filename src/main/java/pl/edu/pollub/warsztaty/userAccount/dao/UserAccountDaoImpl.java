package pl.edu.pollub.warsztaty.userAccount.dao;

import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserAccountDaoImpl implements CustomizedUserAccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(UserAccountEntity u) {
        entityManager.detach(u);
    }

}
