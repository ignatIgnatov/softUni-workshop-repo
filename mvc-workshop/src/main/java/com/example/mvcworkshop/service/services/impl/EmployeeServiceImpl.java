package com.example.mvcworkshop.service.services.impl;

import com.example.mvcworkshop.data.dto.EmployeeDto;
import com.example.mvcworkshop.data.dto.EmployeeRootDto;
import com.example.mvcworkshop.data.entities.Employee;
import com.example.mvcworkshop.data.repositories.EmployeeRepository;
import com.example.mvcworkshop.data.repositories.ProjectRepository;
import com.example.mvcworkshop.service.services.EmployeeService;
import com.example.mvcworkshop.util.XmlParser;
import com.example.mvcworkshop.web.models.EmployeeViewModel;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final String XML_PATH = "src/main/resources/files/xmls/employees.xml";
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository, XmlParser xmlParser, ModelMapper modelMapper, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void importEmployees() throws JAXBException {
        EmployeeRootDto employeeRootDto = this.xmlParser.importXml(EmployeeRootDto.class, XML_PATH);

        for (EmployeeDto employeeDto : employeeRootDto.getEmployeeDtoList()) {
            Employee employee = this.modelMapper.map(employeeDto, Employee.class);
            employee.setProject(this.projectRepository.findByName(employeeDto.getProjectDto().getName()));

            this.employeeRepository.saveAndFlush(employee);
        }
    }

    @Override
    public boolean areImported() {
        return this.employeeRepository.count() >0;
    }

    @Override
    public String readEmployeesXmlFile() {
        String xml = "";
        try {
            xml = String.join("\n", Files.readAllLines(Path.of(XML_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @Override
    public String exportEmployeesWithAgeAbove() {
        // export employees with age above 25
        return findAllByAge().stream().map(EmployeeViewModel::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public List<EmployeeViewModel> findAllByAge() {
        return this.employeeRepository.findAllByAgeGreaterThan(25).stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeViewModel> findAll() {
        return this.employeeRepository.findAll().stream()
                .map(e -> this.modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public String employeesToJson() {
        return this.gson.toJson(findAll());
    }
}
