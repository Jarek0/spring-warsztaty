package pl.edu.pollub.warsztaty.item.service;

import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;

import java.math.BigDecimal;

public interface ItemService {

    public BidEntity placeBid(Long itemId, BigDecimal newBid);

    public ItemEntity createItem(ItemEntity newItem);
}
