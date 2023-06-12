package com.example.mvcworkshop.service.services.impl;

import com.example.mvcworkshop.data.entities.Role;
import com.example.mvcworkshop.data.repositories.RoleRepository;
import com.example.mvcworkshop.service.models.RoleServiceModel;
import com.example.mvcworkshop.service.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedRolesInDb() {
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");

        this.roleRepository.save(admin);
        this.roleRepository.save(user);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
      return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findAll().get(1), RoleServiceModel.class);
    }
}
