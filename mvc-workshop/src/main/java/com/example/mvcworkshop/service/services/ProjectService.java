package com.example.mvcworkshop.service.services;

import com.example.mvcworkshop.web.models.ProjectViewModel;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ProjectService {

    void importProjects() throws JAXBException;

    boolean areImported();

    String readProjectsXmlFile();

    String exportFinishedProjects();

    List<ProjectViewModel> findAllFinishedProjects();
}
