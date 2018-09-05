package pl.edu.pollub.warsztaty.bid.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.exception.ItemNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static pl.edu.pollub.warsztaty.bid.exception.BidNotFoundException.of;

@Component
@RequiredArgsConstructor
public class BidMapper {

    private final BidDao dao;
    private final ItemDao itemDao;

    public BidEntity toEntity(BidDto dto) {
        BidEntity bid = getEntity(dto.getId());

        bid.setAmount(dto.getAmount());
        ItemEntity item = getItem(dto.getItemId());
        item.addBids(bid);

        return bid;
    }

    public List<BidEntity> toEntities(Collection<BidDto> bids) {
        return bids.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    private BidEntity getEntity(Long id) {
        if(isNull(id)) return new BidEntity();
        return dao.findById(id).orElseThrow(() -> of(id));
    }

    private ItemEntity getItem(Long id) {
        return itemDao.findByIdFetchBids(id).orElseThrow(() -> ItemNotFoundException.of(id));
    }

    public BidDto toDto(BidEntity bid) {
        BidDto dto = new BidDto();

        dto.setId(bid.getId());
        dto.setAmount(bid.getAmount());
        dto.setItemId(bid.getItem().getId());

        return dto;
    }

    public List<BidDto> toDtos(Collection<BidEntity> bids) {
        return bids.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
