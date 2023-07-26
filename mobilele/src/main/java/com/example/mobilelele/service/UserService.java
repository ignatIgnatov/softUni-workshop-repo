package com.example.mobilelele.service;

import com.example.mobilelele.model.entity.UserEntity;
import com.example.mobilelele.model.entity.UserRoleEntity;
import com.example.mobilelele.model.service.UserRegisterServiceModel;

import java.util.List;

public interface UserService {

    // return true, if user authenticated successfully
//    boolean authenticate(String username, String password);

    // set CurrentUser to the Logged-In user
//    void loginUser(String username);

    // set CurrentUser to be logout (isAnonymous = true)
//    void logoutUser();

    boolean checkUsername(String username);

    void saveUser(UserRegisterServiceModel userRegisterModel);

    List<UserRoleEntity> getUserRoles();

    UserEntity getUserByName(String name);
}
