package pl.edu.pollub.warsztaty.billingDetails.impl;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;

import javax.persistence.*;


@Entity
@Data
@AttributeOverride(
        name = "owner", column = @Column(name = "cc_owner", nullable = false)
)
@EqualsAndHashCode(callSuper = true, of = {"cardNumber"})
@ToString(callSuper = true)
public class CreditCard extends BillingDetails {

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String expMonth;

    @Column(nullable = false)
    private String expYear;

}
