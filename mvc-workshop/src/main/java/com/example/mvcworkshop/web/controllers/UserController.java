package com.example.mvcworkshop.web.controllers;

import com.example.mvcworkshop.service.services.UserService;
import com.example.mvcworkshop.web.models.UserRegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return super.view("user/register");
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterModel userRegisterModel) {

        if (!userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword())) {
            return super.redirect("/users/register");
        }
//
        this.userService.registerUser(userRegisterModel);
        return super.redirect("/users/login");
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return super.view("user/login");
    }
}
