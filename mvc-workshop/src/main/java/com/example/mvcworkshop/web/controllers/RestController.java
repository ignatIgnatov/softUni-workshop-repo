package com.example.mvcworkshop.web.controllers;

import com.example.mvcworkshop.service.services.EmployeeService;
import com.example.mvcworkshop.service.services.ProjectService;
import com.example.mvcworkshop.web.models.EmployeeViewModel;
import com.example.mvcworkshop.web.models.ProjectViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public RestController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping(value = "/api/employees", produces = "application/json")
    public List<EmployeeViewModel> employees() {
        return this.employeeService.findAll();
    }

    @GetMapping(value = "api/projects", produces = "application/json")
    public List<ProjectViewModel> projects() {
        return this.projectService.findAllFinishedProjects();
    }
}
