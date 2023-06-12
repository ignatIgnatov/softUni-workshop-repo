package com.example.mvcworkshop.service.services;

import com.example.mvcworkshop.service.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String role);
}
