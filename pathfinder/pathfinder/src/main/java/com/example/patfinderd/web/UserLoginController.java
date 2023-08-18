package com.example.patfinderd.web;

import com.example.patfinderd.model.service.UserServiceModel;
import com.example.patfinderd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLoginController {

  private final UserService userService;

  public UserLoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/login")
  public String login() {
    return "login";
  }

//  @PostMapping("/users/login")
//  public String loginUser(UserServiceModel userServiceModel) {
//
//    userService.loginUser(userServiceModel);
//
//    return "redirect:";
//  }
}
