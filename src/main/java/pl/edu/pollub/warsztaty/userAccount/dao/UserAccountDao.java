package pl.edu.pollub.warsztaty.userAccount.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.edu.pollub.warsztaty.userAccount.domain.Gender;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;
import pl.edu.pollub.warsztaty.userAccount.dto.LoginAndCityDto;

import java.util.List;
import java.util.Optional;

public interface UserAccountDao extends JpaRepository<UserAccountEntity, Long>, CustomizedUserAccountDao {

    Optional<UserAccountEntity> findByLogin(String login);

    @Query("select u " +
            "from UserAccountEntity u " +
            "where u.login = :login " +
            "and u.pesel = :pesel")
    Optional<UserAccountEntity> findByLoginAndPesel(@Param("login") String Login, @Param("pesel") String pesel);

    @Query("select u " +
            "from UserAccountEntity u " +
            "where u.firstName = :firstName " +
            "and u.gender = :gender " +
            "order by u.homeAddress.city")
    List<UserAccountEntity> findByFirstNameAndGenderOrderByHomeCity(@Param("firstName") String firstName, @Param("gender") Gender gender);

    @Query("select u.login " +
            "from UserAccountEntity u " +
            "where u.surname = :surname " +
            "and u.homeAddress.street = :street")
    List<String> findLoginBySurnameAndHomeStreet(@Param("surname") String surname, @Param("street") String street);

    @Query("select new pl.edu.pollub.warsztaty.userAccount.dto.LoginAndCityDto(u.login, u.homeAddress.city) " +
            "from UserAccountEntity u " +
            "where u.homeAddress.street = :street")
    List<LoginAndCityDto> findLoginAndCityDtoByHomeStreet(@Param("street") String street);

    @Query("select u.login, u.homeAddress.city " +
            "from UserAccountEntity u " +
            "where u.homeAddress.street = :street")
    List<Object[]> findLoginAndCityByHomeStreet(@Param("street") String street);

    @Query(value = "select u.login, u.street " +
            "from user_account u " +
            "where u.street = ?1",
            nativeQuery = true)
    List<Object[]> findLoginAndCityByHomeStreetRawSql(String street);
}
