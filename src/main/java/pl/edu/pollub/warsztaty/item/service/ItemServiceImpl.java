package pl.edu.pollub.warsztaty.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.exception.ItemNotFoundException;
import pl.edu.pollub.warsztaty.item.validator.BidValidator;
import pl.edu.pollub.warsztaty.item.validator.ItemValidator;

import java.math.BigDecimal;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final BidValidator bidValidator;
    private final ItemValidator itemValidator;

    private final ItemDao itemDao;

    @Transactional(isolation = READ_COMMITTED)
    public BidEntity placeBid(Long itemId, BigDecimal newBidAmount) {

        ItemEntity item = itemDao.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));

        bidValidator.isNewBidGreatestForItem(itemId, newBidAmount);

        BidEntity newBid = new BidEntity(newBidAmount);
        item.addBids(newBid);

        itemDao.save(item);

        return newBid;
    }

    @Transactional
    public ItemEntity createItem(ItemEntity newItem) {
        itemValidator.validateItem(newItem);
        return itemDao.save(newItem);
    }
}
