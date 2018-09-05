package pl.edu.pollub.warsztaty.category.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import javax.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(cascade = {PERSIST})
    @JoinTable(
            name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<ItemEntity> items = new HashSet<>();

    public void addItems(ItemEntity... items) {
        Collections.addAll(this.items, items);
        for(ItemEntity item : items) {
            item.getCategories().add(this);
        }
    }

    public CategoryEntity(String name) {
        this.name = name;
    }
}
