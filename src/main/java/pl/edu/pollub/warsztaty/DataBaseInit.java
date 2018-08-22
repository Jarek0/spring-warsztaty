package pl.edu.pollub.warsztaty;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;

import static pl.edu.pollub.warsztaty.userAccount.factory.UserAccountFactory.createJarek;

@Component
@RequiredArgsConstructor
public class DataBaseInit implements ApplicationListener<ContextRefreshedEvent>{

    private final UserAccountDao userAccountDao;

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

}
