package com.yaskovskyi.webfanfic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String openLoginPage() {

        return "login";
    }

    @RequestMapping("/login_process")
    public String loginUser(@RequestParam(value = "username")String username,
                            @RequestParam(value = "password")String password) {

        return "redirect:/";
    }
}
