package com.example.mvcworkshop.service.services.impl;
import com.example.mvcworkshop.data.dto.CompaniesRootDto;
import com.example.mvcworkshop.data.dto.CompanyDto;
import com.example.mvcworkshop.data.entities.Company;
import com.example.mvcworkshop.data.repositories.CompanyRepository;
import com.example.mvcworkshop.service.services.CompanyService;
import com.example.mvcworkshop.util.XmlParser;
import com.example.mvcworkshop.web.models.CompanyViewModel;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final String XML_PATH = "src/main/resources/files/xmls/companies.xml";
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper, Gson gson) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void importCompanies() throws JAXBException {
       CompaniesRootDto companiesRootDto = this.xmlParser.importXml(CompaniesRootDto.class, XML_PATH);

        for (CompanyDto companyDto : companiesRootDto.getCompanyDtoList()) {
            this.companyRepository.save(this.modelMapper.map(companyDto, Company.class));
        }
    }

    @Override
    public boolean areImported() {
        return this.companyRepository.count() >0;
    }

    @Override
    public String readCompaniesXmlFile() {
        String xml = "";
        try {
          xml = String.join("\n", Files.readAllLines(Path.of(XML_PATH)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xml;
    }

    @Override
    public Set<CompanyViewModel> findAllCompanies() {
        return this.companyRepository.findAll().stream()
                .map(c -> this.modelMapper.map(c, CompanyViewModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public String companyToJson() {
        return this.gson.toJson(findAllCompanies());
    }
}
