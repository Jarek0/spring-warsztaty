package pl.edu.pollub.warsztaty.userAccount.dao;

import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

public interface CustomizedUserAccountDao {

    void detach(UserAccountEntity userAccount);

}
