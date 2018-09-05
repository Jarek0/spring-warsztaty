package pl.edu.pollub.warsztaty.bid.exception;

import pl.edu.pollub.warsztaty.common.exception.ParametrizedException;

import java.util.HashMap;
import java.util.Map;

import static pl.edu.pollub.warsztaty.common.error.Errors.BID_NOT_FOUND;

public class BidNotFoundException extends ParametrizedException {

    private BidNotFoundException(Long id, Map<String, String> params) {
        super(BID_NOT_FOUND, "Bid with id: " + id + " not found", params);
    }

    public static BidNotFoundException of(Long id) {
        Map<String, String> params = new HashMap<String, String>() {{
            put("id", id.toString());
        }};

        return new BidNotFoundException(id, params);
    }
}
