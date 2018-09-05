package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
@ToString(exclude = {"bids"})
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 15)
    private String category;

    @OneToMany(mappedBy = "item", cascade = {ALL}, fetch = LAZY)
    private Set<BidEntity> bids = new HashSet<>();

    @ManyToOne(cascade = PERSIST, fetch = LAZY)
    @JoinColumn(name="user_id")
    private UserAccountEntity owner;

    public void addBids(BidEntity... bids) {
        Collections.addAll(this.bids, bids);
        for(BidEntity bid : bids) {
            bid.setItem(this);
        }
    }
}
