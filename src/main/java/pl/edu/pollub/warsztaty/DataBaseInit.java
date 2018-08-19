package pl.edu.pollub.warsztaty;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.Address;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import static pl.edu.pollub.warsztaty.userAccount.factory.AddressFactory.createJarekBillingAddress;
import static pl.edu.pollub.warsztaty.userAccount.factory.AddressFactory.createJarekHome;
import static pl.edu.pollub.warsztaty.userAccount.factory.UserAccountFactory.createJarek;

@Component
@RequiredArgsConstructor
public class DataBaseInit implements ApplicationListener<ContextRefreshedEvent>{

    private final UserAccountDao userAccountDao;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Address jarekHome = createJarekHome();
        Address jarekBillingAddress = createJarekBillingAddress();
        UserAccountEntity jarek = createJarek(jarekHome, jarekBillingAddress);

        userAccountDao.save(jarek);
    }

}
