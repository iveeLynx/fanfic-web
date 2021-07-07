package com.yaskovskyi.webfanfic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistrationController {

    @RequestMapping("/registration")
    public String openRegistrationPage() {
        return "registration";
    }


}
