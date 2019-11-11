package hibernate.controller;

import hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Service
@Controller
@ComponentScan(basePackages = "hibernate")
public class DeveloperController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/findRichest")

    public String findRichest(ModelMap model) {
        model.addAttribute("richestUser", userService.showRichest());
        return "index";
    }

    @GetMapping(value = "/findAll")
    public String findAll(ModelMap model) {
        model.addAttribute("allUsers", userService.displayAll());
        return "index";
    }

    @GetMapping(value = "/bankSumm")
    public String bankSumm(ModelMap model) {
        model.addAttribute("bankSumm", userService.bankSumm());
        return "index";
    }

    @PostMapping(value = "/findById")
    public String findById(@RequestParam String id, ModelMap model) {
        model.addAttribute("userById", userService.findById(id));
        return "index";
    }
}