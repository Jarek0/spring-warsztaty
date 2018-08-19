package pl.edu.pollub.warsztaty.userAccount.domain;

import lombok.*;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"pesel"})
@SecondaryTables({
        @SecondaryTable(name = "personal_data", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "user_account_id", referencedColumnName = "id")
        })
})
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String login;

    @Column(nullable = false, unique = true, table = "personal_data")
    private String email;

    @Column(nullable = false, length = 30, table = "personal_data")
    private String firstName;

    @Column(nullable = false, length = 30, table = "personal_data")
    private String surname;

    @Enumerated(value = STRING)
    @Column(table = "personal_data")
    private Gender gender;

    @Column(table = "personal_data")
    private LocalDate birthDate;

    @Column(nullable = false, unique = true, length = 11, table = "personal_data")
    private String pesel;

    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "billing_zip_code")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
    })
    private Address billingAddress;
}
