package pl.edu.pollub.warsztaty.item.domain.image;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Parent;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class Image {

    @Parent
    private ItemEntity item;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String filename;

    private int width;

    private int height;

    public Image(String title, String filename, int width, int height) {
        this.title = title;
        this.filename = filename;
        this.width = width;
        this.height = height;
    }
}
