package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.UserEntity;
import com.example.mobilele.mobilele.repository.UserRepository;
import com.example.mobilele.mobilele.security.CurrentUser;
import com.example.mobilele.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final CurrentUser currentUser;

   @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
       this.currentUser = currentUser;
   }

    @Override
    public boolean authenticate(String username, String password) {

       Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

       if (userEntityOptional.isEmpty()) {
           return false;
       }
        return passwordEncoder.matches(password, userEntityOptional.get().getPassword());
    }

    @Override
    public void loginUser(String username) {
       currentUser
               .setAnonymous(false)
               .setName(username);
    }


}