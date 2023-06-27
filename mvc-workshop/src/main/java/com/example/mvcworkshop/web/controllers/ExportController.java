package com.example.mvcworkshop.web.controllers;

import com.example.mvcworkshop.service.services.CompanyService;
import com.example.mvcworkshop.service.services.EmployeeService;
import com.example.mvcworkshop.service.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService, CompanyService companyService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping("/project-if-finished")
    public ModelAndView finishedProjects() {
        ModelAndView modelAndView = new ModelAndView("/export/export-project-if-finished.html");
        String projectsIfFinished = this.projectService.exportFinishedProjects();
        modelAndView.addObject("projectsIfFinished", projectsIfFinished);
        return modelAndView;
    }

    @GetMapping("/employees-above")
    public ModelAndView employeesAbove() {
        ModelAndView modelAndView = new ModelAndView("/export/export-employees-with-age.html");
        String employeesAbove = this.employeeService.exportEmployeesWithAgeAbove();
        modelAndView.addObject("employeesAbove", employeesAbove);
        return modelAndView;
    }

    @GetMapping("/employees-json")
    public ModelAndView employeesToJson() {
        ModelAndView modelAndView = new ModelAndView("/export/export-employees-json.html");
        String allEmployees = this.employeeService.employeesToJson();
        modelAndView.addObject("allEmployees", allEmployees);
        return modelAndView;
    }

    @GetMapping("/companies-json")
    public ModelAndView companiesToJson() {
        ModelAndView modelAndView = new ModelAndView("/export/export-companies-json.html");
        String allCompanies = this.companyService.companyToJson();
        modelAndView.addObject("allCompanies", allCompanies);
        return modelAndView;
    }

}
