package cgg.blogapp.blogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String customLogin() {

        return "login";
    }

    @GetMapping("/logout")
    public String customlogout() {

        return "logout";
    }

}
