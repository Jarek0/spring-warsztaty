package pl.edu.pollub.warsztaty.billingDetails.domain.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.edu.pollub.warsztaty.billingDetails.domain.BillingDetails;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {"account"})
@ToString(callSuper = true)
public class BankAccount extends BillingDetails {


    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String swift;


}
