package com.example.patfinderd.service;

import com.example.patfinderd.model.service.UserServiceModel;

public interface UserService {

    void loginUser(UserServiceModel userServiceModel);

    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);
}
