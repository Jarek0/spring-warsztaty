package pl.edu.pollub.warsztaty.billingDetails.factory;

import pl.edu.pollub.warsztaty.billingDetails.domain.impl.CreditCardEntity;

public class CreditCardFactory {

    public static CreditCardEntity of(String cardNumber, String expMonth, String expYear, String owner) {
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpMonth(expMonth);
        creditCard.setExpYear(expYear);
        creditCard.setOwner(owner);
        return creditCard;
    }
}
