package net.ardou.tinylink.user;

import javax.persistence.*;

@Entity(name = "site_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String mail;

    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Role role;

    @Column(columnDefinition = "boolean default false")
    boolean banned;


    public static User createUser(String mail, String password) {
        User user = new User();
        user.mail = mail;
        user.password = password;
        user.role = Role.USER;
        user.banned = false;
        return user;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void ban() {
        this.banned = true;
    }

    public boolean isBanned() {
        return banned;
    }
}
