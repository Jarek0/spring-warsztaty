package pl.edu.pollub.warsztaty.item.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.dto.ItemDto;

import java.util.Optional;

import static pl.edu.pollub.warsztaty.common.error.ErrorUtils.rejectValue;
import static pl.edu.pollub.warsztaty.common.error.Errors.*;

@Component
@RequiredArgsConstructor
public class ItemValidator implements Validator {

    private final ItemDao dao;

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemDto item = (ItemDto) o;
        hasUniqueName(item, errors);
        isOnlyFourItemsInCategory(item, errors);
    }


    private void hasUniqueName(ItemDto item, Errors errors) {
        String name = item.getName();
        Optional<ItemEntity> maybeItem = dao.findByNameAndIdNot(name, item.getId());

        if(maybeItem.isPresent()) {
            rejectValue(errors, ITEM_VALIDATION_NOT_UNIQUE_NAME);
        }
    }


    private void isOnlyFourItemsInCategory(ItemDto item, Errors errors) {
        String category = item.getCategory();
        Long itemsCountInCategory = dao.countByCategoryAndIdNot(category, item.getId());

        if(itemsCountInCategory >= 4) {
            rejectValue(errors, ITEM_VALIDATION_TOO_MANY_ITEMS_IN_CATEGORY);
        }
    }
}
