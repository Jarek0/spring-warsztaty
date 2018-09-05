package pl.edu.pollub.warsztaty.item.exception;

import pl.edu.pollub.warsztaty.common.exception.ParametrizedException;

import java.util.HashMap;
import java.util.Map;

import static pl.edu.pollub.warsztaty.common.error.Errors.ITEM_NOT_FOUND;

public class ItemNotFoundException extends ParametrizedException {

    private ItemNotFoundException(Long id, Map<String, String> params) {
        super(ITEM_NOT_FOUND, "Item with id: " + id + " not found", params);
    }

    private ItemNotFoundException(String name, Map<String, String> params) {
        super(ITEM_NOT_FOUND, "Item with name: " + name + " not found", params);
    }

    public static ItemNotFoundException of(String name) {
        Map<String, String> params = new HashMap<String, String>() {{
            put("name", name);
        }};

        return new ItemNotFoundException(name, params);
    }

    public static ItemNotFoundException of(Long id) {
        Map<String, String> params = new HashMap<String, String>() {{
            put("id", id.toString());
        }};

        return new ItemNotFoundException(id, params);
    }
}
