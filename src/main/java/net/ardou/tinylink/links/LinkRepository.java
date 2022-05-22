package net.ardou.tinylink.links;

import net.ardou.tinylink.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> getAllByOwnerId(Long userId);

    Optional<Link> getLinkByLink(String shortLink);
}
