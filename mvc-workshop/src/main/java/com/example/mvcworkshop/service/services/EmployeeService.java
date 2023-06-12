package com.example.mvcworkshop.service.services;

import com.example.mvcworkshop.web.models.EmployeeViewModel;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface EmployeeService {

    void importEmployees() throws JAXBException;

    boolean areImported();

    String readEmployeesXmlFile();

    String exportEmployeesWithAgeAbove();

    List<EmployeeViewModel> findAllByAge();

    List<EmployeeViewModel> findAll();

    String employeesToJson();
}
