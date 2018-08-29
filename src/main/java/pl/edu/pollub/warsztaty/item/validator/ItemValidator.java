package pl.edu.pollub.warsztaty.item.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemValidator {

    private final ItemDao itemDao;

    public void validateItem(ItemEntity newItem) {
        hasUniqueName(newItem.getName());
        isOnlyFourItemsInCategory(newItem.getCategory());
    }

    private void hasUniqueName(String name) {
        Optional<ItemEntity> maybeItem = itemDao.findByName(name);

        if(maybeItem.isPresent()) throw new IllegalArgumentException("Item with name: " + name + " already exists");
    }


    private void isOnlyFourItemsInCategory(String category) {
        Integer itemsCountInCategory = itemDao.getItemsCountForCategory(category);

        if(itemsCountInCategory >= 4) throw new IllegalArgumentException("Only four items can exist in category : " + category);
    }

}
