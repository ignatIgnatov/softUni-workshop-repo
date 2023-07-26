package com.example.mobilelele.web;

import com.example.mobilelele.model.entity.ModelEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller // can contain multiple actions, like @Component
public class HomeController {

    @GetMapping("/home") // request mapping to endpoint, used method GET
    public String home(ModelEntity model, @AuthenticationPrincipal Principal principal){
        return "index"; // return response with resource (html, json...)
    } // http://localhost:8080/home

    // or

//    @RequestMapping(value = "/home", method = RequestMethod.POST) // Request mapping
//    public ModelAndView home(ModelAndView mav) { // action/method
//        mav.setViewName("home-view");
//        return mav; // returned response view
//    }
}
