package pl.edu.pollub.warsztaty.bid.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import javax.persistence.*;

import java.math.BigDecimal;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bid")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"amount", "item"})
public class BidEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Column(nullable = false)
    private BigDecimal amount;

    public BidEntity(BigDecimal amount) {
        this.amount = amount;
    }
}
