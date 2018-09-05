package pl.edu.pollub.warsztaty.userAccount.domain;

import lombok.Getter;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"), ROLE_USER("ROLE_USER");

    @Getter
    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }
}
