package net.ardou.tinylink.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ;

    final String name;

    Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
