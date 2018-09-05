package pl.edu.pollub.warsztaty.item.dto;

import lombok.Data;
import pl.edu.pollub.warsztaty.common.validation.StartsWithSameLetter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@StartsWithSameLetter(name = "name", category = "category", message = "item.validation.category.firstLetter")
public abstract class ItemDto {

    protected Long id;

    @NotNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż0-9]{3,10}$", message = "item.validation.name.pattern")
    protected String name;

    @NotNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{5,15}$", message = "item.validation.category.pattern")
    protected String category;

}
