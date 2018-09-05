package pl.edu.pollub.warsztaty.common.error;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Errors {
    static final String FATAL_ERROR = "internalServerError";

    public static final String BID_VALIDATION_ITEM_NOT_EXIST = "bid.validation.amount.itemNotExist";
    public static final String BID_VALIDATION_AMOUNT_TOO_SMALL = "bid.validation.amount.tooSmall";
    public static final String BID_NOT_FOUND = "bid.notFound";

    public static final String ITEM_VALIDATION_NOT_UNIQUE_NAME = "item.validation.name.unique";
    public static final String ITEM_VALIDATION_TOO_MANY_ITEMS_IN_CATEGORY = "item.validation.category.tooManyItems";
    public static final String ITEM_NOT_FOUND = "item.notFound";
}
