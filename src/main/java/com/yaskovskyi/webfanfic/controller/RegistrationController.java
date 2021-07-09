package com.yaskovskyi.webfanfic.controller;

import com.yaskovskyi.webfanfic.util.HttpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @RequestMapping("/registration")
    public String openRegistrationPage() {
        return "registration";
    }

    @RequestMapping("/process_register")
    public String processRegistration(@RequestParam (value = "first_name", required = false) String firstname,
                                      @RequestParam (value = "last_name", required = false) String lastname,
                                      @RequestParam (value = "user_name") String username,
                                      @RequestParam (value = "email") String email,
                                      @RequestParam (value = "user_password") String password,
                                      Model model) {
        String result = HttpService.getInstance().sendNewUserData(firstname,lastname,username,email,password);
        String message = "Registration successful.";
        if(result.equals("false")){
            message = "Sorry but there is an error. Try register another user.";
        }
        model.addAttribute("message", message);
        return "registration_result";
    }

}
