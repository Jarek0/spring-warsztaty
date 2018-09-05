package pl.edu.pollub.warsztaty.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.pollub.warsztaty.bid.dto.BidDto;
import pl.edu.pollub.warsztaty.bid.serializer.BidSerializer;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ItemRes extends ItemDto {

    @JsonProperty
    @JsonSerialize(contentUsing = BidSerializer.class)
    private List<BidDto> bids;

}
