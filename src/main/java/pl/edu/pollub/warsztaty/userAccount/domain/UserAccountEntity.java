package pl.edu.pollub.warsztaty.userAccount.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account")
@Data
@Builder
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String login;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 30)
    private String firstName;

    @Column(nullable = false, unique = true, length = 30)
    private String surname;

    @Enumerated(value = STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Column(nullable = false, unique = true, length = 11)
    private String pesel;

}
