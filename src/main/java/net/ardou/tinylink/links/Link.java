package net.ardou.tinylink.links;

import net.ardou.tinylink.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "link")
@Table(
        indexes = {@Index(columnList = "owner_id"), @Index(columnList = "link")},
        uniqueConstraints = @UniqueConstraint(columnNames = "link"))
public class Link implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    public User owner;

    public String link;

    public String outLink;

    public Link() {
    }

    public Link(String link, String outLink, User owner) {
        this.link = link;
        this.outLink = outLink;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getOutLink() {
        return outLink;
    }
}
