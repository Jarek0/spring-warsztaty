package pl.edu.pollub.warsztaty.userAccount.dao;

import pl.edu.pollub.warsztaty.userAccount.domain.Gender;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import java.util.List;

public interface CustomizedUserAccountDao {

    void detach(UserAccountEntity userAccount);

    List<UserAccountEntity> findByFirstNameAndGenderOrderByHomeCityCriteriaApi(String firstName, Gender gender);

    void clear();
}
