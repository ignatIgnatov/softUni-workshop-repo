package com.example.mvcworkshop.service.services;

import com.example.mvcworkshop.data.entities.Company;
import com.example.mvcworkshop.web.models.CompanyViewModel;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.util.Set;

public interface CompanyService {

    void importCompanies() throws JAXBException;

    boolean areImported();

    String readCompaniesXmlFile();

    Set<CompanyViewModel> findAllCompanies();

    String companyToJson();
}
