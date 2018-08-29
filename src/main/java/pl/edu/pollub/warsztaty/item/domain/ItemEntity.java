package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.item.domain.image.Image;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    @Column(unique = true, nullable = false)
    private String name;

    private String category;

    @OneToMany(mappedBy = "item", cascade = {ALL}, fetch = LAZY)
    private List<BidEntity> bids = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "images")
    @CollectionId(
            columns = @Column(name = "image_id"),
            type = @Type(type = "long"),
            generator = "identity"
    )
    private Collection<Image> images = new ArrayList<>();

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY)
    private UserAccountEntity user;

    public void addImages(Image... images) {
        Collections.addAll(this.images, images);
    }

    public void addBids(BidEntity... bids) {
        Collections.addAll(this.bids, bids);
        for(BidEntity bid : bids) {
            bid.setItem(this);
        }
    }

    public void addBids(Double... bids) {
        List<BidEntity> newBids = Arrays.stream(bids).map(b -> new BidEntity(BigDecimal.valueOf(b))).collect(Collectors.toList());
        this.bids.addAll(newBids);
        for(BidEntity bid : newBids) {
            bid.setItem(this);
        }
    }

    public ItemEntity(String name) {
        this.name = name;
    }

    public ItemEntity(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
