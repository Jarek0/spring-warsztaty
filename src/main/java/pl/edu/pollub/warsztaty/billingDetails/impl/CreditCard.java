package pl.edu.pollub.warsztaty.billingDetails.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AttributeOverride(
        name = "owner", column = @Column(name = "cc_owner", nullable = false)
)
@EqualsAndHashCode(callSuper = true, of = {"cardNumber"})
public class CreditCard extends BillingDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String expMonth;

    @Column(nullable = false)
    private String expYear;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccountEntity userAccount;

}
