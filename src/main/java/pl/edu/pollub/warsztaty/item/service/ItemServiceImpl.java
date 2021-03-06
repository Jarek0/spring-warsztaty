package pl.edu.pollub.warsztaty.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.bid.dao.BidDao;
import pl.edu.pollub.warsztaty.bid.domain.BidEntity;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.bid.mapper.BidMapper;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.ItemMapper.ItemMapper;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.item.domain.ItemEntity;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.dto.ItemSimRes;

import static pl.edu.pollub.warsztaty.item.exception.ItemNotFoundException.of;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;
    private final ItemMapper mapper;

    private final BidDao bidDao;
    private final BidMapper bidMapper;

    @Transactional
    @Override
    public ItemRes create(ItemReq req) {
        ItemEntity item = mapper.toEntity(req);
        item = dao.save(item);
        return mapper.toRes(item);
    }

    @Transactional(readOnly = true)
    @Override
    public ItemRes read(Long id) {
        ItemEntity item = dao.findByIdFetchBids(id)
                .orElseThrow(() -> of(id));
        return mapper.toRes(item);
    }

    @Transactional
    @Override
    public ItemRes update(ItemReq req) {
        ItemEntity item = mapper.toEntity(req);
        item = dao.save(item);
        return mapper.toRes(item);
    }

    @Transactional
    @Override
    public ItemRes delete(Long id) {
        ItemEntity item = dao.findById(id)
                .orElseThrow(() -> of(id));
        dao.delete(item);
        return mapper.toRes(item);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDto<ItemSimRes> readPage(Pageable pageable) {
        return mapper.toPageDto(dao.findAll(pageable));
    }

    @Override
    public BidDto placeBid(BidDto newBid) {
        BidEntity bid = bidMapper.toEntity(newBid);
        bid = bidDao.save(bid);
        return bidMapper.toDto(bid);
    }
}
