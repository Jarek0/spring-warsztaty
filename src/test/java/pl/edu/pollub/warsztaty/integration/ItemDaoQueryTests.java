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
import pl.edu.pollub.warsztaty.item.dto.CategoryAmountAvgDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class ItemDaoQueryTests {

    @Autowired
    private ItemDao itemDao;

    @Test
    public void shouldFindByBidCount() {
        List<ItemEntity> items = itemDao.findByBidCountGreater(2);
        assertEquals(3, items.size());
    }

    @Test
    public void shouldFindByBidMaxAmountGreater() {
        List<ItemEntity> items = itemDao.findAnyBidAmountGreaterThan(BigDecimal.valueOf(200));
        assertEquals(3, items.size());
    }

    @Test
    public void shouldFindByBidMaxAmountLesserThan() {
        List<ItemEntity> items = itemDao.findByBidMaxAmountLesserThan(BigDecimal.valueOf(300));
        assertEquals(3, items.size());
    }

    @Test
    public void shouldFindCategoryAmountAvg() {
        List<CategoryAmountAvgDto> categoriesAvg = itemDao.findCategoryAmountAvg();
        assertEquals(4, categoriesAvg.size());
    }

    @Test
    public void shouldFindWithBidsWhichAmountGreaterThan() {
        List<ItemEntity> items = itemDao.findWithBidsWhichAmountGreaterThan(BigDecimal.valueOf(200));
        assertEquals(8, items.size());
    }
}
