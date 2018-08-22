package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "image")
    @Column(name = "filename")
    @CollectionId(
            columns = @Column(name = "image_id"),
            type = @Type(type = "long"),
            generator = "identity"
    )
    private Collection<String> images = new ArrayList<>();

    public void addImages(String... images) {
        Collections.addAll(this.images, images);
    }
}
