package com.example.judgeV2.service;

import com.example.judgeV2.model.entity.RoleNameEnum;
import com.example.judgeV2.model.entity.UserEntity;
import com.example.judgeV2.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void login(UserServiceModel user);

    void logout();

    List<String> findAllUsernames();

    void changeRole(String username, RoleNameEnum roleNameEnum);

    UserEntity findById(Long id);
}
