package com.example.mvcworkshop.service.services;

import com.example.mvcworkshop.service.models.UserServiceModel;
import com.example.mvcworkshop.web.models.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    UserServiceModel registerUser(UserRegisterModel userRegisterModel);
}
