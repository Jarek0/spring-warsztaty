package pl.edu.pollub.warsztaty.item.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.dto.ItemDto;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.filter.ItemFilter;

public interface ItemService {

    ItemRes create(ItemReq dto);

    ItemRes read(Long id);

    ItemRes update(ItemReq dto);

    ItemRes delete(Long id);

    PageDto<ItemRes> readPage(Pageable pageable, ItemFilter filter);

    BidDto placeBid(BidDto bidDto);

}