package usst.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jyzxc
 * @since 2024-12-12
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/advertisements")
    public String advertisements() {
        return "advertisements";
    }

    @GetMapping("/article")
    public String articles() {
        return "updateAd";
    }

    @GetMapping("/markdown")
    public String markdown() {
        return "edit";
    }

    @GetMapping("/updateAdDetails")
    public String updateAdDetails() {
        return "updateAdDetails";
    }
}
