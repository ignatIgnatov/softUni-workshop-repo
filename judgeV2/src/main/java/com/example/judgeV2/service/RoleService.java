package com.example.judgeV2.service;

import com.example.judgeV2.model.entity.RoleEntity;
import com.example.judgeV2.model.entity.RoleNameEnum;

public interface RoleService {
    void initRoles();

    RoleEntity findRole(RoleNameEnum roleNameEnum);
}
