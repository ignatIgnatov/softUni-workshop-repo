package com.example.judgeV2.service.impl;

import com.example.judgeV2.model.entity.RoleEntity;
import com.example.judgeV2.model.entity.RoleNameEnum;
import com.example.judgeV2.repository.RoleRepository;
import com.example.judgeV2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initRoles() {
        if (roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity(RoleNameEnum.ADMIN);
            RoleEntity user = new RoleEntity(RoleNameEnum.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }
    }

    @Override
    public RoleEntity findRole(RoleNameEnum roleNameEnum) {


        return roleRepository
                .findByName(roleNameEnum)
                .orElse(null);
    }
}
