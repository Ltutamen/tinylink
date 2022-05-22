package net.ardou.tinylink.links;

import net.ardou.tinylink.error.ErrorService;
import net.ardou.tinylink.user.User;
import net.ardou.tinylink.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping(path = TinylinkController.LINKS_ENDPOINT)
@Secured("USER")
public class TinylinkController {

    @Value("${tinylink.domain}")
    String domain;

    public static final String LINKS_ENDPOINT = "/links";
    static final String LINKS_VIEW = "links";

    final LinkService linkService;
    final ErrorService errorService;

    public TinylinkController(LinkService linkService, ErrorService errorService) {
        this.linkService = linkService;
        this.errorService = errorService;
    }

    @GetMapping
    String get() {
        return LINKS_VIEW;
    }

    @PostMapping(path = "/create")
    ModelAndView create(@ModelAttribute CreateLinkBody body) {
        final String outLink = body.getOutLink();

        try {
            URI outURI = URI.create(outLink);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = ((UserPrincipal)authentication.getPrincipal()).getUser();
            linkService.addLink(outURI, user);

        } catch (IllegalArgumentException e) {
            return errorService.withErrorMessage("Not a valid URI string");
        }

        return new ModelAndView(new RedirectView(LINKS_ENDPOINT));
    }

    @PostMapping(path = "/delete")
    String delete() {
        return LINKS_VIEW;
    }

    @ModelAttribute("links")
    List<Link> links() {
        return linkService.getLinks();
    }

    @ModelAttribute("domain")
    String domain() {
        return domain;
    }
}
