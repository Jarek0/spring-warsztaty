package pl.edu.pollub.warsztaty.item.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.bid.validator.BidValidator;
import pl.edu.pollub.warsztaty.common.dto.PageDto;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;
import pl.edu.pollub.warsztaty.item.dto.ItemRes;
import pl.edu.pollub.warsztaty.item.dto.ItemSimRes;
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

    @InitBinder("bidDto")
    public void initBidValidator(WebDataBinder binder) {
        binder.setValidator(bidValidator);
    }

    @InitBinder("itemReq")
    public void initItemValidator(WebDataBinder binder) {
        binder.setValidator(itemValidator);
    }

    @PostMapping
    public ItemRes create(@RequestBody @Valid ItemReq itemReq) {
        return itemService.create(itemReq);
    }

    @GetMapping("/{id}")
    public ItemRes read(@PathVariable Long id) {
        return itemService.read(id);
    }

    @PatchMapping
    public ItemRes update(@RequestBody @Valid ItemReq itemReq) {
        return itemService.update(itemReq);
    }

    @DeleteMapping("/{id}")
    public ItemRes delete(@PathVariable Long id) {
        return itemService.delete(id);
    }

    @GetMapping
    public PageDto<ItemSimRes> readPage(Pageable pageable) {
        return itemService.readPage(pageable);
    }

    @PostMapping("/bid")
    public BidDto placeBid(@RequestBody @Valid BidDto bidDto) {
        return itemService.placeBid(bidDto);
    }
}
