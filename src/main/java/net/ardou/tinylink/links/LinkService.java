package net.ardou.tinylink.links;

import net.ardou.tinylink.user.User;
import net.ardou.tinylink.user.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService {
    final LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }

    @Transactional
    void addLink(URI toUrl, User owner) {
        String clearUri = toUrl.toString().split("://")[1];
        Link link = new Link(UUID.randomUUID().toString(), clearUri, owner);

        repository.save(link);
    }

    void deleteLink(String link) {

    }

    void editLink() {

    }

    List<Link> getLinks() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        final Long userId = userPrincipal.getUserId();

        return repository.getAllByOwnerId(userId);
    }
}
