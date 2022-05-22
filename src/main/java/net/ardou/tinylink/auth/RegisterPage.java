package net.ardou.tinylink.auth;

import net.ardou.tinylink.auth.domain.RegisterBody;
import net.ardou.tinylink.config.WebSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static net.ardou.tinylink.userswitch.UserSwitch.USER_SWITCH_ENDPOINT;

@RequestMapping(RegisterPage.REGISTER_VIEW_NAME)
@Controller
public class RegisterPage {
    public static final String REGISTER_ENDPOINT = "/register";
    public static final String REGISTER_VIEW_NAME = "register";

    final RegisterService service;

    public RegisterPage(RegisterService service) {
        this.service = service;
    }

    @GetMapping
    String get() {
        return REGISTER_VIEW_NAME;
    }

    @PostMapping
    String post(@ModelAttribute RegisterBody body, Model model) {
        var registerResult = service.registerUser(body);

        if (registerResult.isEmpty()) {
            return "redirect:%s".formatted(WebSecurityConfig.LOGIN_ENDPOINT);
        } else {
            model.addAttribute("errormsg", registerResult.get());
            return REGISTER_VIEW_NAME;
        }
    }


}
