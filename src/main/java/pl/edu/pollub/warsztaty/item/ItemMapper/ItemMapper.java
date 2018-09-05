package pl.edu.pollub.warsztaty.item.ItemMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.bid.mapper.BidMapper;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.dto.ItemSimRes;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static pl.edu.pollub.warsztaty.item.exception.ItemNotFoundException.of;

@Component
@RequiredArgsConstructor
public class ItemMapper {

    private final ItemDao dao;

    private final BidMapper bidMapper;

    public ItemEntity toEntity(ItemReq dto) {
        ItemEntity item = getEntity(dto.getId());

        item.setName(dto.getName());
        item.setCategory(dto.getCategory());

        return item;
    }

    private ItemEntity getEntity(Long id) {
        if(isNull(id)) return new ItemEntity();
        return dao.findById(id).orElseThrow(() -> of(id));
    }

    public ItemRes toRes(ItemEntity item) {
        ItemRes dto = new ItemRes();

        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(item.getCategory());
        dto.setBids(bidMapper.toDtos(item.getBids()));

        return dto;
    }

    private ItemSimRes toSimRes(ItemEntity item) {
        ItemSimRes dto = new ItemSimRes();

        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(item.getCategory());

        return dto;
    }

    private List<ItemSimRes> toRes(Collection<ItemEntity> items) {
        return items.stream()
                .map(this::toSimRes)
                .collect(Collectors.toList());
    }

    public PageDto<ItemSimRes> toPageDto(Page<ItemEntity> page) {
        int number = page.getNumber();
        int total = page.getTotalPages();
        List<ItemSimRes> dtos = this.toRes(page.getContent());

        return new PageDto<>(number, total, dtos);
    }
}
