package net.ardou.tinylink.redirect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = RedirectController.REDIRECT_ENDPOINT)
@Controller
public class RedirectController {
    public static final String REDIRECT_ENDPOINT = "/r";

    static final String HTTPS_REDIRECT_PREFIX = "redirect:https://%s";

    final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("{shortUri}")
    String redirect(@PathVariable(required = false) String shortUri) {

        return redirectService.getRedirectUri(shortUri)
                .map(HTTPS_REDIRECT_PREFIX::formatted).orElseThrow();
    }
}
