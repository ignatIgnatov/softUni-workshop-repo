package com.example.mvcworkshop.service.services.impl;

import com.example.mvcworkshop.data.dto.ProjectDto;
import com.example.mvcworkshop.data.dto.ProjectsRootDto;
import com.example.mvcworkshop.data.entities.Project;
import com.example.mvcworkshop.data.repositories.CompanyRepository;
import com.example.mvcworkshop.data.repositories.ProjectRepository;
import com.example.mvcworkshop.web.models.ProjectViewModel;
import com.example.mvcworkshop.service.services.ProjectService;
import com.example.mvcworkshop.util.XmlParser;
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
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final String XML_PATH = "src/main/resources/files/xmls/projects.xml";
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }


    @Override
    public void importProjects() throws JAXBException {
        ProjectsRootDto projectsRootDto = this.xmlParser.importXml(ProjectsRootDto.class, XML_PATH);

        for (ProjectDto projectDto : projectsRootDto.getProjectDtoList()) {
               Project project = this.modelMapper.map(projectDto, Project.class);
               project.setCompany(this.companyRepository.findByName(projectDto.getCompanyDto().getName()));

               this.projectRepository.saveAndFlush(project);
        }
    }

    @Override
    public boolean areImported() {
        return this.projectRepository.count() >0;
    }

    @Override
    public String readProjectsXmlFile() {
        String xml = "";
        try {
            xml = String.join("\n", Files.readAllLines(Path.of(XML_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @Override
    public String exportFinishedProjects(){
        StringBuilder sb = new StringBuilder();

        List<ProjectViewModel> projects = findAllFinishedProjects();

        for (ProjectViewModel project : projects) {
            sb.append(String.format("Project Name: %s", project.getName()))
                    .append(System.lineSeparator())
                    .append(String.format("  Description: %s", project.getDescription()))
                    .append(System.lineSeparator())
                    .append(String.format("  Salary: %.2f", project.getPayment()))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public List<ProjectViewModel> findAllFinishedProjects() {
        return this.projectRepository.findAllByIsFinishedTrue().stream()
                .map(p -> this.modelMapper.map(p, ProjectViewModel.class))
                .collect(Collectors.toList());
    }
}
