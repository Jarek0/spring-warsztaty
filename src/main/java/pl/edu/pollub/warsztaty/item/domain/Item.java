package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;

import javax.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "image", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "filename")
    private Set<String> images = new HashSet<>();

    public void addImages(String... images) {
        Collections.addAll(this.images, images);
    }
}
