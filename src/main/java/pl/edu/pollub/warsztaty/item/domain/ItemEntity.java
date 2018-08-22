package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import pl.edu.pollub.warsztaty.bid.BidEntity;
import pl.edu.pollub.warsztaty.item.domain.image.Image;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "item", cascade = {PERSIST}, fetch = LAZY)
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

    public ItemEntity(String name) {
        this.name = name;
    }
}
