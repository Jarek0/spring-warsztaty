package pl.edu.pollub.warsztaty.bid.exception;

import java.math.BigDecimal;

public class BidAmountLesserThanMax extends IllegalArgumentException {
    public BidAmountLesserThanMax(BigDecimal max, BigDecimal newBid, Long itemId) {
        super("New bid has amount: " + newBid + " lesser than max bid amount: " + max + " for item with id: " + itemId);
    }
}
