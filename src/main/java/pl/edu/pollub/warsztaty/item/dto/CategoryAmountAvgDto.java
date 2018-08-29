package pl.edu.pollub.warsztaty.item.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAmountAvgDto {

    String category;

    double amountAvg;
}
