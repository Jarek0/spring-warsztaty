package pl.edu.pollub.warsztaty.billingDetails;


import lombok.Data;
import lombok.ToString;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@Data
@ToString
public abstract class BillingDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String owner;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccountEntity userAccount;
}
