package net.ardou.tinylink.admin;

import net.ardou.tinylink.user.User;

public class UserDto {
    Long id;
    String role;
    String mail;
    boolean banned;

    public UserDto(User user) {
        this.id = user.getId();
        this.role = user.getRole().name();
        this.mail = user.getMail();
        this.banned = user.isBanned();
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
