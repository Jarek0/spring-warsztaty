package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.bid.BidEntity;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.domain.image.Image;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class ItemDaoTests {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldHasThreeImages() {
        ItemEntity item = new ItemEntity("some item");
        item.addImages(
                new Image("foo", "foo.jpg", 20, 20),
                new Image("baz", "baz.jpg", 20, 20),
                new Image("bar", "bar.jpg", 20, 20),
                new Image("bar", "bar.jpg", 20, 20)
        );

        itemDao.save(item);

        ItemEntity foundItem = itemDao.findAll().get(0);

        assertEquals(4, foundItem.getImages().size());
    }

    @Test
    public void shouldHasFourBids() {
        ItemEntity item = new ItemEntity("some item");

        item.addBids(
                new BidEntity(BigDecimal.valueOf(1)),
                new BidEntity(BigDecimal.valueOf(2)),
                new BidEntity(BigDecimal.valueOf(3)),
                new BidEntity(BigDecimal.valueOf(4))
        );

        item = itemDao.save(item);

        entityManager.clear();

        ItemEntity foundItem = itemDao.findOne(item.getId());

        assertNotNull(foundItem.getBids().get(0));
    }
}
