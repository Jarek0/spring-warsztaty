package pl.edu.pollub.warsztaty;

import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.item.dto.ItemReq;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ItemReqFactory {

    public static ItemReq of(String name, String category) {
        ItemReq req = new ItemReq();
        req.setName(name);
        req.setCategory(category);
        return req;
    }
}
