package net.ardou.tinylink.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPage {

    @GetMapping("/")
    String get() {
        return "index";
    }
}
