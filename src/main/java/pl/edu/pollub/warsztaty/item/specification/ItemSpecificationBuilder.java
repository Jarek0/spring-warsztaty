package pl.edu.pollub.warsztaty.item.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.common.specification.Filter;
import pl.edu.pollub.warsztaty.common.specification.SpecificationBuilder;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.filter.ItemFilter;

@Component
public class ItemSpecificationBuilder extends SpecificationBuilder<ItemEntity, ItemFilter>{

    public ItemSpecificationBuilder() {
        super(ItemFilter.class);
    }

    @Override
    public Specification<ItemEntity> create(ItemFilter filter) {
        return super.create(filter);
    }
}
