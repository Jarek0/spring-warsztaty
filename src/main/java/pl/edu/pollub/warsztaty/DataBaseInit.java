package pl.edu.pollub.warsztaty;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pollub.warsztaty.item.dao.ItemDao;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;


@Component
@RequiredArgsConstructor
public class DataBaseInit implements ApplicationListener<ContextRefreshedEvent>{

    private final UserAccountDao userAccountDao;
    private final ItemDao itemDao;

    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }

}
