package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;
import pl.edu.pollub.warsztaty.userAccount.dto.LoginAndCityDto;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static pl.edu.pollub.warsztaty.userAccount.domain.Gender.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class UserAccountDaoQueryTests {

    @Autowired
    private UserAccountDao userAccountDao;

    @Test
    public void shouldFindByLoginAndPesel() {
        Optional<UserAccountEntity> maybeUserAccount = userAccountDao.findByLoginAndPesel("jarek1234", "12312312344");
        assertTrue(maybeUserAccount.isPresent());
    }

    @Test
    public void shouldFindByFirstNameAndGenderOrderByHomeCity() {
        List<UserAccountEntity> userAccounts = userAccountDao.findByFirstNameAndGenderOrderByHomeCity("Weronika", FAMALE);
        assertEquals(2, userAccounts.size());
    }

    @Test
    public void shouldFindLoginBySurnameAndHomeStreet() {
        List<String> logins = userAccountDao.findLoginBySurnameAndHomeStreet("Nowak", "7 Maja");
        assertEquals(3, logins.size());
    }

    @Test
    public void shouldFindLoginAndCityDtoByHomeStreet() {
        List<LoginAndCityDto> loginAndCityDtos = userAccountDao.findLoginAndCityDtoByHomeStreet("2 Maja");
        assertEquals(5, loginAndCityDtos.size());
    }

    @Test
    public void shouldFindLoginAndCityByHomeStreet() {
        List<Object[]> loginsAndCities = userAccountDao.findLoginAndCityByHomeStreet("2 Maja");
        assertEquals(5, loginsAndCities.size());
    }

    @Test
    public void findLoginAndCityByHomeStreetRawSql() {
        List<Object[]> loginsAndCities = userAccountDao.findLoginAndCityByHomeStreetRawSql("2 Maja");
        assertEquals(5, loginsAndCities.size());
    }
}
