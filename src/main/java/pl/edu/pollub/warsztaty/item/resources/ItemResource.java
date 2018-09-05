package pl.edu.pollub.warsztaty.item.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.bid.validator.BidValidator;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.filter.ItemFilter;
import pl.edu.pollub.warsztaty.item.service.ItemService;
import pl.edu.pollub.warsztaty.item.validator.ItemValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemResource {

    private final ItemService itemService;

    private final ItemValidator itemValidator;

    private final BidValidator bidValidator;

    @InitBinder("dto")
    public void initItemValidator(WebDataBinder binder) {
        binder.addValidators(itemValidator);
    }

    @InitBinder("bidDto")
    public void initBidValidator(WebDataBinder binder) {
        binder.addValidators(bidValidator);
    }

    @PostMapping
    public ItemRes create(@RequestBody @Valid ItemReq dto) {
        return itemService.create(dto);
    }

    @GetMapping("/{id}")
    public ItemRes read(@PathVariable Long id) {
        return itemService.read(id);
    }

    @PatchMapping
    public ItemRes update(@RequestBody @Valid ItemReq dto) {
        return itemService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ItemRes delete(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @GetMapping
    public PageDto<ItemRes> readPage(Pageable pageable, ItemFilter filter) {
        return itemService.readPage(pageable, filter);
    }

    @PostMapping("/bid")
    public BidDto placeBid(@RequestBody @Valid BidDto bidDto) {
        return itemService.placeBid(bidDto);
    }
}
