package pl.edu.pollub.warsztaty.billingDetails;


import lombok.Data;
import lombok.ToString;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy = TABLE_PER_CLASS)
@Data
@ToString
public abstract class BillingDetails {

    @Id
    @SequenceGenerator(name = "billing_details_id_generator", sequenceName = "billing_details_seq", allocationSize = 1)
    @GeneratedValue(generator = "billing_details_id_generator", strategy = SEQUENCE) //nie da się użyć identity
    private Long id;

    @Column(nullable = false)
    protected String owner;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccountEntity userAccount;
}
