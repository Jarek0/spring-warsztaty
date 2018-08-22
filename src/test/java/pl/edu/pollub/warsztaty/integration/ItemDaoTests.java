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

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

        itemDao.save(item);

        ItemEntity foundItem = itemDao.findAll().get(0);

        assertEquals(4, foundItem.getBids().size());
        Optional<BidEntity> maybeBid = foundItem.getBids().stream().findAny();
        assertTrue(maybeBid.isPresent());
        assertNotNull(maybeBid.get());
    }
}
