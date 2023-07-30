package com.example.cookies;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LanguageController {

    private final String defaultLang = "bg";
    private final List<String> allLangs = List.of("en", "bg", "de");

    //Cookie
//    @PostMapping("/save")
//    public String save(@RequestParam String lang, HttpServletResponse response) {
//
//        Cookie cookie = new Cookie("langCookie", lang);
//        response.addCookie(cookie);
//
//        return "redirect:/all";
//    }

    //Session
    @PostMapping("/save")
    public String save(@RequestParam String lang, HttpServletResponse response, HttpSession session) {

        session.setAttribute("lang", lang);
        return "redirect:/all";
    }

    //Cookie
//    @GetMapping("/all")
//    public String allLangs(Model model,
//                           @CookieValue(value = "langCookie", required = false, defaultValue = defaultLang) String lang) {
//        model.addAttribute("all", allLangs);
//        model.addAttribute("preferredLang", lang);
//        return "languages";
//    }

    //Session
    @GetMapping("/all")
    public String allLangs(Model model,
                           HttpSession session) {
        Object preferredLang = session.getAttribute("lang");
        if (preferredLang == null) {
            preferredLang = defaultLang;
        }
        model.addAttribute("all", allLangs);
        model.addAttribute("preferredLang", preferredLang);
        return "languages";
    }

}
