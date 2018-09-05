package pl.edu.pollub.warsztaty.userAccount.domain;

import lombok.*;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"login"})
@SecondaryTables({
        @SecondaryTable(name = "personal_data", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "user_account_id", referencedColumnName = "id")
        })
})
@ToString(exclude = {"items"})
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

    @OneToMany(mappedBy = "owner", cascade = ALL)
    private Set<ItemEntity> items;

    public void addItems(ItemEntity... items) {
        Collections.addAll(this.getItems(), items);
        for(ItemEntity item : items) {
            item.setOwner(this);
        }
    }
}
