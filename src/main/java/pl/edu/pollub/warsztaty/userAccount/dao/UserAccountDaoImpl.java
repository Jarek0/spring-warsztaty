package pl.edu.pollub.warsztaty.userAccount.dao;

import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.userAccount.domain.Gender;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserAccountDaoImpl implements CustomizedUserAccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(UserAccountEntity u) {
        entityManager.detach(u);
    }

    @Override
    public List<UserAccountEntity> findByFirstNameAndGenderOrderByHomeCityCriteriaApi(String firstName, Gender gender) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserAccountEntity> q = cb.createQuery(UserAccountEntity.class);
        Root<UserAccountEntity> u = q.from(UserAccountEntity.class);

        q.select(u)
                .where(
                        cb.and(
                                cb.equal(u.get("firstName"), cb.parameter(String.class, "firstName")),
                                cb.equal(u.get("gender"), cb.parameter(Gender.class, "gender"))
                        ))
                .orderBy(cb.asc(u.get("homeAddress").get("city")));

        return entityManager.createQuery(q)
                .setParameter("firstName", firstName)
                .setParameter("gender", gender)
                .getResultList();
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

}
