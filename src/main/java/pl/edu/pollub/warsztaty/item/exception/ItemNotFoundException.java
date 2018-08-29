package pl.edu.pollub.warsztaty.item.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super("Item with id: " + id + " not found");
    }
}
