package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.domain.image.Image;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class ItemDaoTests {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void shouldHasThreeImages() {
        ItemEntity item = new ItemEntity();
        item.addImages(
                new Image("foo", "foo.jpg", 20, 20),
                new Image("baz", "baz.jpg", 20, 20),
                new Image("bar", "bar.jpg", 20, 20),
                new Image("bar", "bar.jpg", 20, 20)
        );

        itemDao.save(item);

        ItemEntity foundItem = itemDao.findAll().get(0);

        assertEquals(3, foundItem.getImages().size());
    }
}
