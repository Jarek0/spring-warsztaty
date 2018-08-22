package pl.edu.pollub.warsztaty.item.domain;

import lombok.Data;

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
    @OrderColumn
    @Column(name = "filename")
    private List<String> images = new ArrayList<>();

    public void addImages(String... images) {
        Collections.addAll(this.images, images);
    }
}
