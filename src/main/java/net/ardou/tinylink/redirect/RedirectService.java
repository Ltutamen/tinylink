package net.ardou.tinylink.redirect;

import net.ardou.tinylink.links.Link;
import net.ardou.tinylink.links.LinkRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedirectService {
    final LinkRepository linkRepository;

    public RedirectService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Optional<String> getRedirectUri(String shortUri) {
        return linkRepository.getLinkByLink(shortUri)
                .map(Link::getOutLink);
    }
}
