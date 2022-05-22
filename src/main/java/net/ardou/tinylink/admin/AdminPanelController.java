package net.ardou.tinylink.admin;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = AdminPanelController.ADMIN_PANEL_ENDPOINT)
@Secured("ADMIN")
public class AdminPanelController {
    public static final String ADMIN_PANEL_ENDPOINT = "/adminpanel";
    static final String ADMIN_PANEL_VIEW = "admin";

    final AdminService adminService;

    AdminPanelController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    String get() {
        return ADMIN_PANEL_VIEW;
    }

    @PostMapping("/promote")
    String promote(@RequestBody String mail) {
        adminService.promoteToAdmin(mail);

        return get();
    }

    @PostMapping("/ban")
    String ban(String mail) {
        adminService.banUser(mail);

        return get();
    }

    @ModelAttribute
    List<UserDto> users() {
        return adminService.getUsers();
    }
}
