package com.example.mobilele.mobilele.web;

import com.example.mobilele.mobilele.model.service.UserLoginServiceModel;
import com.example.mobilele.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @ModelAttribute("userModel")
    public UserLoginServiceModel userModel() {
        return new UserLoginServiceModel();
    }


    @GetMapping("/users/login")
    public String showLogin() {
        return "auth-login";
    }
}
