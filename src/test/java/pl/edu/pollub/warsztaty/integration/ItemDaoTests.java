package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.Item;

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
        Item item = new Item();
        item.addImages("foo.jpg", "bar.jpg", "bar.jpg", "baz.jpg");

        itemDao.save(item);

        Item foundItem = itemDao.findAll().get(0);

        assertEquals(4, foundItem.getImages().size());
        assertEquals("foo.jpg", foundItem.getImages().get(0));
        assertEquals("bar.jpg", foundItem.getImages().get(1));
        assertEquals("bar.jpg", foundItem.getImages().get(2));
        assertEquals("baz.jpg", foundItem.getImages().get(3));
        /*
        foundItem.getImages().add(2, "b.jpg");

        foundItem = itemDao.save(foundItem); //wiele instrukcji update
        */
    }
}
