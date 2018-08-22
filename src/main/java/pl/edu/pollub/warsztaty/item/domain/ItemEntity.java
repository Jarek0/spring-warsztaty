package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
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
    private Set<Image> images = new HashSet<>();

    public void addImages(Image... images) {
        Collections.addAll(this.images, images);
    }
}
