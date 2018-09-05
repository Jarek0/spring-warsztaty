package pl.edu.pollub.warsztaty.item.filter;

import lombok.Data;
import pl.edu.pollub.warsztaty.common.specification.Filter;
import pl.edu.pollub.warsztaty.common.specification.FilterBy;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import static pl.edu.pollub.warsztaty.common.specification.Constraints.LIKE;

@Data
public class ItemFilter implements Filter {

    @FilterBy(field = "id")
    private Long id;

    @FilterBy(field = "name", constraints = LIKE)
    private String name;

    @FilterBy(field = "category", constraints = LIKE)
    private String category;
}
