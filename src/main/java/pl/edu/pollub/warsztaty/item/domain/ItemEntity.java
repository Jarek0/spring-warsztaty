package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;
import pl.edu.pollub.warsztaty.item.domain.image.Image;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "item")
@Data
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "images")
    @CollectionId(
            columns = @Column(name = "image_id"),
            type = @Type(type = "long"),
            generator = "identity"
    )
    private Collection<Image> images = new ArrayList<>();

    public void addImages(Image... images) {
        Collections.addAll(this.images, images);
    }
}
