package com.example.mobilelele.web;

import com.example.mobilelele.model.service.UserLoginServiceModel;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @ModelAttribute("userModel") // create UserLoginServiceModel for all methods
    public UserLoginServiceModel userModel() {
        return new UserLoginServiceModel();
    }

    @GetMapping("/users/login")
    public String getLogin() {
        return "auth-login";
    }

    // show errors for invalid login
    @PostMapping("/users/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                              String username,
                              RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("bad_credentials", true);
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

//    @GetMapping("/users/logout")
//    public String logout() {
////        userService.logoutUser();
//        return "redirect:/";
//    }

//    @PostMapping("/users/login")
//    public String login(@Valid // validate userModel
//                        @ModelAttribute UserLoginServiceModel userModel, // instantiate before Post
//                        BindingResult bindingResult,  // if errors in validation, preserved here
//                        RedirectAttributes redirectAttributes) { // when BindingResult.hasErrors() redirect with preserved parameters
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("userModel", userModel);
//            redirectAttributes.addFlashAttribute(
//                    "org.springframework.validation.BindingResult.userModel", bindingResult);
//            return "redirect:/users/login";
//        }
//
//        if (userService.authenticate(userModel.getUsername(), userModel.getPassword())) {
//            userService.loginUser(userModel.getUsername());
//            return "redirect:/";
//        } else { // wrong password, correct according UserLoginServiceModel, but not exist in DB
//            redirectAttributes.addFlashAttribute("userModel", userModel);
//            redirectAttributes.addFlashAttribute("notFound", true);
//            return "redirect:/users/login";
//        }
//    }
}
