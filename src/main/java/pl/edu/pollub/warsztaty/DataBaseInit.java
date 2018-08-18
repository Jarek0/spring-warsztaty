package pl.edu.pollub.warsztaty;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;

@Component
@RequiredArgsConstructor
public class DataBaseInit implements ApplicationListener<ContextRefreshedEvent>{

    private final UserAccountDao userAccountDao;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

    }

}
