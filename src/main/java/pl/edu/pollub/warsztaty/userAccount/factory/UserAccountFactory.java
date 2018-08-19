package pl.edu.pollub.warsztaty.userAccount.factory;

import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static pl.edu.pollub.warsztaty.userAccount.domain.Gender.MALE;

@NoArgsConstructor(access = PRIVATE)
public final class UserAccountFactory {

    public static UserAccountEntity of(Long id, String pesel) {
        return UserAccountEntity.builder()
                .id(id)
                .login("default")
                .email("default@gmail.com")
                .firstName("default")
                .surname("default")
                .gender(MALE)
                .birthDate(LocalDate.of(1994, 8, 4))
                .pesel(pesel)
                .build();
    }

    public static UserAccountEntity of(String login, String pesel, Address homeAddress) {
        return UserAccountEntity.builder()
                .login(login)
                .email(login + "@gmail.com")
                .firstName("default")
                .surname("default")
                .gender(MALE)
                .birthDate(LocalDate.of(1994, 8, 4))
                .pesel(pesel)
                .homeAddress(homeAddress)
                .build();
    }

    public static UserAccountEntity createJarek(Address jarekHome) {
        return UserAccountEntity.builder()
                .login("jarek123")
                .email("jarek123@gmail.com")
                .firstName("Jarek")
                .surname("Bielec")
                .gender(MALE)
                .birthDate(LocalDate.of(1994, 8, 4))
                .pesel("99999999999")
                .homeAddress(jarekHome)
                .build();
    }

    public static UserAccountEntity createJarek(Address jarekHome, Address billingAddress) {
        return UserAccountEntity.builder()
                .login("jarek123")
                .email("jarek123@gmail.com")
                .firstName("Jarek")
                .surname("Bielec")
                .gender(MALE)
                .birthDate(LocalDate.of(1994, 8, 4))
                .pesel("99999999999")
                .homeAddress(jarekHome)
                .billingAddress(billingAddress)
                .build();
    }

    public static UserAccountEntity createAndrzej(Address andrzejHome) {
        return UserAccountEntity.builder()
                .login("jarek123")
                .email("andrzej123@gmail.com")
                .firstName("Andrzej")
                .surname("Aaaaa")
                .gender(MALE)
                .birthDate(LocalDate.of(1994, 7, 4))
                .pesel("99999999998")
                .homeAddress(andrzejHome)
                .build();
    }
}
