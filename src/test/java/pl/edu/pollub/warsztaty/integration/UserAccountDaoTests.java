package pl.edu.pollub.warsztaty.integration;

import com.sun.deploy.security.ruleset.ExceptionRule;
import org.hibernate.ObjectNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static pl.edu.pollub.warsztaty.utils.UserAccountFactory.createAndrzej;
import static pl.edu.pollub.warsztaty.utils.UserAccountFactory.createJarek;
import static pl.edu.pollub.warsztaty.utils.UserAccountFactory.of;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserAccountDaoTests {

    @Autowired
    private UserAccountDao userAccountDao;

    @Test
    public void shouldSaveJarek() {
        UserAccountEntity jarek = createJarek(); //Przejsciowy

        userAccountDao.save(jarek); //Trwaly

        assertNotNull(jarek.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotSaveBecauseUserWithThisLoginExists() {
        UserAccountEntity jarek = createJarek(); //Przejsciowy

        userAccountDao.save(jarek); //Trwaly

        assertNotNull(jarek.getId());

        UserAccountEntity andrzej = createAndrzej();

        userAccountDao.save(andrzej);
    }

    @Test
    public void shouldFindJarekByLogin() {
        UserAccountEntity jarek = createJarek(); //Przejsciowy

        userAccountDao.save(jarek); //Trwaly

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        assertEquals("99999999999", foundJarek.getPesel());
    }

    @Test
    public void shouldUpdateUserAccount() {
        UserAccountEntity jarek = createJarek(); //Przejsciowy

        userAccountDao.save(jarek); //Trwaly

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        foundJarek.setFirstName("Andrzej");

        //noinspection UnnecessaryLocalVariable
        UserAccountEntity andrzej = jarek;

        userAccountDao.save(andrzej);

        UserAccountEntity foundAndrzej = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        assertEquals("Andrzej", foundAndrzej.getFirstName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldRemoveUserAccount() {
        UserAccountEntity jarek = createJarek(); //Przejsciowy

        userAccountDao.save(jarek); //Trwaly

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        userAccountDao.delete(foundJarek); //Usuniety

        //noinspection ResultOfMethodCallIgnored
        userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));
    }

    @Test
    public void shouldSaveAllUserAccounts() {
        List<UserAccountEntity> users = Arrays.asList( //Przejsciowy
                of("Jarek", "999999999"),
                of("Andrzej", "999999998"),
                of("Bartek", "999999997"),
                of("Lukasz", "999999996")
        );

        userAccountDao.save(users); //Trwaly

        List<UserAccountEntity> foundUsers = userAccountDao.findAll();

        assertEquals(4, foundUsers.size());
    }

}
