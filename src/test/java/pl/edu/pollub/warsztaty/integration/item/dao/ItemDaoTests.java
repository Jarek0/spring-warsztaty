package pl.edu.pollub.warsztaty.integration.item.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
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

    @Autowired
    private EntityManager entityManager;

    @Before
    public void cleanDb() {
        itemDao.deleteAll();
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

        Optional<ItemEntity> maybeFoundItem = itemDao.findItemWithBids(item.getId());

        assertTrue(maybeFoundItem.isPresent());
        ItemEntity foundItem = maybeFoundItem.get();
        assertNotNull(foundItem.getBids().get(0));
    }

    @Test
    public void shouldRemoveItemsByCategory() {
        ItemEntity item1 = new ItemEntity("item1", "category1");
        ItemEntity item2 = new ItemEntity("item2", "category1");
        ItemEntity item3 = new ItemEntity("item3", "category2");
        ItemEntity item4 = new ItemEntity("item4", "category2");

        itemDao.save(Arrays.asList(item1, item2, item3, item4));

        assertEquals(4 , itemDao.findAll().size());

        itemDao.deleteByCategory("category2");

        assertEquals(2 , itemDao.findAll().size());
    }
}
