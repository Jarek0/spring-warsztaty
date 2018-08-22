package pl.edu.pollub.warsztaty.billingDetails.factory;

import pl.edu.pollub.warsztaty.billingDetails.domain.impl.CreditCard;

public class CreditCardFactory {

    public static CreditCard of(String cardNumber, String expMonth, String expYear, String owner) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(cardNumber);
        creditCard.setExpMonth(expMonth);
        creditCard.setExpYear(expYear);
        creditCard.setOwner(owner);
        return creditCard;
    }
}
