package pl.edu.pollub.warsztaty.item.service;

import org.springframework.data.domain.Pageable;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.dto.ItemSimRes;

public interface ItemService {

    ItemRes create(ItemReq req);

    ItemRes read(Long id);

    ItemRes update(ItemReq req);

    ItemRes delete(Long id);

    PageDto<ItemSimRes> readPage(Pageable pageable);

    BidDto placeBid(BidDto bidDto);

}
