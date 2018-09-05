package pl.edu.pollub.warsztaty.userAccount.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"login"})
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String login;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

}
