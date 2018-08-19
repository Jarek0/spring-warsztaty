package pl.edu.pollub.warsztaty.billingDetails;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BillingDetails {

    @Column(nullable = false)
    protected String owner;

}
