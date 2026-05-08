package se.jensen.duaa.cloudstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.jensen.duaa.cloudstore.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";

    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password) {

        userService.register(username, password);
        return "redirect:/login";
    }
}
