package pl.edu.pollub.warsztaty.common.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.pollub.warsztaty.security.dto.AuthUser;
import pl.edu.pollub.warsztaty.userAccount.dao.UserAccountDao;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountEntity user = this.dao.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No appUser found with username '%s'.", username)));


        return new AuthUser(user);
    }

}
