package com.example.mobilele.mobilele.web;

import com.example.mobilele.mobilele.model.service.UserLoginServiceModel;
import com.example.mobilele.mobilele.security.CurrentUser;
import com.example.mobilele.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

   private final UserService userService;

   @Autowired
    public LoginController(CurrentUser currentUser, UserService userService) {
       this.userService = userService;
   }

    @PostMapping("/users/login")
    public String login(UserLoginServiceModel model) {

    if (userService.authenticate(model.getUsername(), model.getPassword())) {
        userService.loginUser(model.getUsername());
        return "redirect:/";
    }
    return "redirect:/users/login";
    }

    @GetMapping("/users/login")
    public String showLogin() {
        return "auth-login";
    }

    @PostMapping("/users/logout")
    public String logout() {
       userService.logoutCurrentUser();
        return "redirect:/";
    }
}
