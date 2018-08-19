package pl.edu.pollub.warsztaty.billingDetails.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {"account"})
public class BankAccount extends BillingDetails {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String swift;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccountEntity userAccount;

}
