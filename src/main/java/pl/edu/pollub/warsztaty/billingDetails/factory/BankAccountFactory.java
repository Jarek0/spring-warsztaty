package pl.edu.pollub.warsztaty.billingDetails.factory;

import pl.edu.pollub.warsztaty.billingDetails.domain.impl.BankAccount;

public class BankAccountFactory {

    public static BankAccount of(String account, String bankName, String swift, String owner) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount(account);
        bankAccount.setBankName(bankName);
        bankAccount.setSwift(swift);
        bankAccount.setOwner(owner);
        return bankAccount;
    }
}
