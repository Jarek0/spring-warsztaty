package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.billingDetails.domain.BillingDetails;
import pl.edu.pollub.warsztaty.billingDetails.dao.BillingDetailsDao;
import pl.edu.pollub.warsztaty.billingDetails.factory.BankAccountFactory;
import pl.edu.pollub.warsztaty.billingDetails.factory.CreditCardFactory;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.impl.GermanZipCode;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.impl.SwissZipCode;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static pl.edu.pollub.warsztaty.userAccount.factory.AddressFactory.*;
import static pl.edu.pollub.warsztaty.userAccount.factory.UserAccountFactory.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class UserAccountDaoTests {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private BillingDetailsDao billingDetailsDao;

    @Test
    public void shouldSaveBillingDetails() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        jarek.addBillingDetails(
                BankAccountFactory.of("123","Alior", "Alior", "jarek"),
                BankAccountFactory.of("124","Alior", "Alior", "jarek"),
                BankAccountFactory.of("125","Alior", "Alior", "jarek"),
                CreditCardFactory.of("123","1", "2018", "jarek"),
                CreditCardFactory.of("124","1", "2018", "jarek"),
                CreditCardFactory.of("125","1", "2018", "jarek")
        );

        userAccountDao.save(jarek);

        List<BillingDetails> billingDetails = billingDetailsDao.findAll();

        assertEquals(6, billingDetails.size());
    }

    @Test
    public void shouldHasGermanZipCode() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        assertTrue(foundJarek.getHomeAddress().getZipCode() instanceof GermanZipCode);
    }

    @Test
    public void shouldHasSwissZipCode() {
        Address andrzejHome = createAndrzejHome();
        UserAccountEntity andrzej = createAndrzej(andrzejHome);

        userAccountDao.save(andrzej);

        UserAccountEntity foundAndrzej = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        assertTrue(foundAndrzej.getHomeAddress().getZipCode() instanceof SwissZipCode);
    }

    @Test
    public void shouldSaveJarek() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        assertNotNull(jarek.getId());
    }

    @Test
    public void shouldSaveJarekWithBillingAddress() {
        Address jarekHome = createJarekHome();
        Address jarekBillingAddress = createJarekBillingAddress();
        UserAccountEntity jarek = createJarek(jarekHome, jarekBillingAddress);

        userAccountDao.save(jarek);

        assertNotNull(jarek.getId());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void shouldNotSaveBecauseUserWithThisLoginExists() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        assertNotNull(jarek.getId());

        Address andrzejHome = createAndrzejHome();
        UserAccountEntity andrzej = createAndrzej(andrzejHome);

        userAccountDao.save(andrzej);
    }

    @Test
    public void shouldFindJarekByLogin() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        assertEquals("99999999999", foundJarek.getPesel());
    }

    @Test
    public void shouldUpdateUserAccount() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

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
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        UserAccountEntity foundJarek = userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));

        userAccountDao.delete(foundJarek);

        //noinspection ResultOfMethodCallIgnored
        userAccountDao.findByLogin("jarek123")
                .orElseThrow(() -> new ObjectNotFoundException("jarek123", "UserAccount"));
    }

    @Test
    public void shouldSaveAllUserAccounts() {
        Address homeAddress = createJarekHome();
        List<UserAccountEntity> users = Arrays.asList(
                of("Jarek", "999999999", homeAddress),
                of("Andrzej", "999999998", homeAddress),
                of("Bartek", "999999997", homeAddress),
                of("Lukasz", "999999996", homeAddress)
        );

        userAccountDao.save(users);

        List<UserAccountEntity> foundUsers = userAccountDao.findAll();

        assertEquals(4, foundUsers.size());
    }

    @Test
    public void shouldNotSaveUserAccount() {
        Address jarekHome = createJarekHome();
        UserAccountEntity jarek = createJarek(jarekHome);

        userAccountDao.save(jarek);

        userAccountDao.detach(jarek);

        jarek.setFirstName("Andrzej");

    }

}
