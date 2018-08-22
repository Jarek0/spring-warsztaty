package pl.edu.pollub.warsztaty.integration;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pollub.warsztaty.category.dao.CategoryDao;
import pl.edu.pollub.warsztaty.category.domain.CategoryEntity;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Slf4j
public class CategoryDaoTests {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ItemDao itemDao;

    @Test
    public void shouldSaveCategoriesWithItems() {
        CategoryEntity electronics = new CategoryEntity("electronics");
        CategoryEntity automotive = new CategoryEntity("automotive");
        CategoryEntity clothes = new CategoryEntity("clothes");

        ItemEntity accumulator = new ItemEntity("accumulator");
        ItemEntity laptop = new ItemEntity("laptop");
        ItemEntity tomato = new ItemEntity("tomato");
        ItemEntity tShirt = new ItemEntity("tShirt");
        ItemEntity foo = new ItemEntity("foo");
        ItemEntity bar = new ItemEntity("bar");

        electronics.addItems(accumulator, laptop, bar);
        automotive.addItems(accumulator, tomato, foo);
        clothes.addItems(tShirt, foo, bar);

        categoryDao.save(Arrays.asList(electronics, automotive, clothes));

        List<ItemEntity> items = itemDao.findAll();
        assertEquals(6, items.size());

        List<CategoryEntity> categories = categoryDao.findAll();
        assertEquals(3, categories.size());

        Optional<CategoryEntity> maybeFoundAutomotive = categories.stream()
                .filter(c -> c.getName().equals("automotive"))
                .findAny();

        assertTrue(maybeFoundAutomotive.isPresent());

        CategoryEntity foundAutomotive = maybeFoundAutomotive.get();
        assertEquals(3, foundAutomotive.getItems().size());

        boolean containsTomato = foundAutomotive.getItems().stream()
                .anyMatch(i -> i.getName().equals("tomato"));
        assertTrue(containsTomato);
    }
}
