package pl.edu.pollub.warsztaty.billingDetails.domain;


import lombok.Data;
import lombok.ToString;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = JOINED)
@Data
@ToString
public abstract class BillingDetailsEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    protected String owner;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccountEntity userAccount;
}
