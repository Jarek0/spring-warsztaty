package pl.edu.pollub.warsztaty.bid.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BidDto {

    private Long id;

    @DecimalMin(value = "0.00", message = "bid.validation.amount.zero")
    private BigDecimal amount;

    @NotNull
    private Long itemId;
}
