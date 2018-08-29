package pl.edu.pollub.warsztaty.item.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.exception.BidAmountLesserThanMax;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class BidValidator {

    private final BidDao bidDao;

    public void isNewBidGreatestForItem(Long itemId, BigDecimal newBid) {
        BigDecimal maxBidForItem = bidDao.findMaxBidForItem(itemId);

        if(nonNull(maxBidForItem) && maxBidForItem.compareTo(newBid) > 0) throw new BidAmountLesserThanMax(maxBidForItem, newBid, itemId);
    }
}
