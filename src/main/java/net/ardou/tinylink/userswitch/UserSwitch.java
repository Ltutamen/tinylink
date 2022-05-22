package net.ardou.tinylink.userswitch;

import net.ardou.tinylink.admin.AdminPanelController;
import net.ardou.tinylink.links.TinylinkController;
import net.ardou.tinylink.user.Role;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = UserSwitch.USER_SWITCH_ENDPOINT)
public class UserSwitch {

    public static final String USER_SWITCH_ENDPOINT = "/registerswitch";

    @GetMapping
    String get() {
        var authority = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next();

        if (authority.getAuthority().equals(Role.USER.name())) {
            return "redirect:%s".formatted(TinylinkController.LINKS_ENDPOINT);
        } else if (authority.getAuthority().equals(Role.ADMIN.name())) {
            return "redirect:%s".formatted(AdminPanelController.ADMIN_PANEL_ENDPOINT);
        }

        throw new IllegalStateException("Unknown user role:[%s]".formatted(authority));

    }
}
