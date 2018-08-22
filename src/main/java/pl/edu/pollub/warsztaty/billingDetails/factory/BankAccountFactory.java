package pl.edu.pollub.warsztaty.billingDetails.factory;

import pl.edu.pollub.warsztaty.billingDetails.domain.impl.BankAccountEntity;

public class BankAccountFactory {

    public static BankAccountEntity of(String account, String bankName, String swift, String owner) {
        BankAccountEntity bankAccount = new BankAccountEntity();
        bankAccount.setAccount(account);
        bankAccount.setBankName(bankName);
        bankAccount.setSwift(swift);
        bankAccount.setOwner(owner);
        return bankAccount;
    }
}
