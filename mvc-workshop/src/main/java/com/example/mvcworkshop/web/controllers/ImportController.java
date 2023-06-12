package com.example.mvcworkshop.web.controllers;

import com.example.mvcworkshop.service.services.CompanyService;
import com.example.mvcworkshop.service.services.EmployeeService;
import com.example.mvcworkshop.service.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    @Autowired
    public ImportController(ProjectService projectService, EmployeeService employeeService, CompanyService companyService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping("/xml")
    public ModelAndView xmls() {
        ModelAndView modelAndView = new ModelAndView("xml/import-xml");
        boolean[] areImported = new boolean[] {
                this.companyService.areImported(),
                this.projectService.areImported(),
                this.employeeService.areImported()
        };
        modelAndView.addObject("areImported", areImported);
        return modelAndView;
    }

    @GetMapping("/companies")
    public ModelAndView companies() {
        ModelAndView modelAndView = new ModelAndView("xml/import-companies");
        String xmlContent = this.companyService.readCompaniesXmlFile();
        modelAndView.addObject("companies", xmlContent);
        return modelAndView;
    }

    @PostMapping("/companies")
    public ModelAndView companiesConfirm() throws JAXBException {
        this.companyService.importCompanies();

        return this.redirect("/import/xml");
    }

    @GetMapping("/projects")
    public ModelAndView projects() {
        ModelAndView modelAndView = new ModelAndView("xml/import-projects");
        String xmlContent = this.projectService.readProjectsXmlFile();
        modelAndView.addObject("projects", xmlContent);
        return modelAndView;
    }

    @PostMapping("/projects")
    public ModelAndView projectsConfirm() throws JAXBException {
        this.projectService.importProjects();

        return this.redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView employees() {
        ModelAndView modelAndView = new ModelAndView("xml/import-employees");
        String xmlContent = this.employeeService.readEmployeesXmlFile();
        modelAndView.addObject("employees", xmlContent);
        return modelAndView;
    }

    @PostMapping("/employees")
    public ModelAndView employeesConfirm() throws JAXBException {
        this.employeeService.importEmployees();

        return this.redirect("/import/xml");
    }

}
