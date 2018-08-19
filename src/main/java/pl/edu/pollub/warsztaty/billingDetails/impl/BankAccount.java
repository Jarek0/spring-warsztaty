package pl.edu.pollub.warsztaty.billingDetails.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;

import javax.persistence.*;


@Entity
@Data
@EqualsAndHashCode(callSuper = true, of = {"account"})
@ToString(callSuper = true)
public class BankAccount extends BillingDetails {


    @Column(unique = true)
    private String account;

    private String bankName;

    private String swift;


}
