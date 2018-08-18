package pl.edu.pollub.warsztaty.utils;

import lombok.NoArgsConstructor;
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
}
