package pl.edu.pollub.warsztaty;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;
import pl.edu.pollub.warsztaty.billingDetails.factory.BankAccountFactory;
import pl.edu.pollub.warsztaty.billingDetails.factory.CreditCardFactory;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;

import java.util.Set;

import static pl.edu.pollub.warsztaty.userAccount.factory.AddressFactory.createJarekHome;
import static pl.edu.pollub.warsztaty.userAccount.factory.UserAccountFactory.createJarek;

@Component
@RequiredArgsConstructor
public class DataBaseInit implements ApplicationListener<ContextRefreshedEvent>{

    private final UserAccountDao userAccountDao;

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

}
