package com.example.mvcworkshop.web.controllers;

import com.example.mvcworkshop.service.services.CompanyService;
import com.example.mvcworkshop.service.services.EmployeeService;
import com.example.mvcworkshop.service.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final CompanyService companyService;

    @Autowired
    public HomeController(EmployeeService employeeService, ProjectService projectService, CompanyService companyService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.companyService = companyService;
    }


    @GetMapping("/")
    public ModelAndView index() {
        return super.view("index");
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        boolean areImported = this.employeeService.areImported()
                && this.projectService.areImported()
                && this.companyService.areImported();
        modelAndView.addObject("areImported", areImported);
        return modelAndView;
    }

}
