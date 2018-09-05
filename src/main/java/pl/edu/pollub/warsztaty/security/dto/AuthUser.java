package pl.edu.pollub.warsztaty.security.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import java.util.Collection;
import java.util.Date;

@Data
public class AuthUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Date lastPasswordReset;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;
    private Boolean enabled = true;


    public AuthUser(UserAccountEntity user) {
        this.setId(user.getId());
        this.setUsername(user.getLogin());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setLastPasswordReset(null);
        this.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getRoleName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.getEnabled();
    }
}
