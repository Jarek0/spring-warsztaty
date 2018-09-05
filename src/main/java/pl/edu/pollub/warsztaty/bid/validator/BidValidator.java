package pl.edu.pollub.warsztaty.bid.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;

import java.math.BigDecimal;
import java.util.Optional;

import static java.util.Objects.nonNull;
import static pl.edu.pollub.warsztaty.common.error.ErrorUtils.rejectValue;
import static pl.edu.pollub.warsztaty.common.error.Errors.*;

@Component
@RequiredArgsConstructor
public class BidValidator implements Validator {

    private final ItemDao itemDao;
    private final BidDao dao;


    @Override
    public boolean supports(Class<?> aClass) {
        return BidDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BidDto bid = (BidDto) o;

        doesItemExist(bid.getItemId(), errors);
        isNewBidGreatestForItem(bid, errors);
    }

    private void doesItemExist(Long itemId, Errors e) {
        if(!itemDao.findById(itemId).isPresent()) {
            rejectValue(e, BID_VALIDATION_ITEM_NOT_EXIST);
        }
    }


    private void isNewBidGreatestForItem(BidDto newBid, Errors e) {
        Optional<BigDecimal> maybeMaxBid = dao.findMaxBidForItem(newBid.getItemId());

        if(maybeMaxBid.isPresent() && maybeMaxBid.get().compareTo(newBid.getAmount()) > 0) {
            rejectValue(e, BID_VALIDATION_AMOUNT_TOO_SMALL);
        }
    }
}
