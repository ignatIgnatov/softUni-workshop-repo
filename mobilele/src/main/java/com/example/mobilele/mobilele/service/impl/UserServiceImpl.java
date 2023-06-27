package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.UserEntity;
import com.example.mobilele.mobilele.model.entities.UserRoleEntity;
import com.example.mobilele.mobilele.model.entities.enums.UserRoleEnum;
import com.example.mobilele.mobilele.repository.UserRepository;
import com.example.mobilele.mobilele.security.CurrentUser;
import com.example.mobilele.mobilele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

      UserEntity user = userRepository.findByUsername(username).orElseThrow();

      List<UserRoleEnum> userRoles = user
              .getUserRoles()
              .stream()
              .map(UserRoleEntity::getRole)
              .collect(Collectors.toList());

       currentUser
               .setAnonymous(false)
               .setName(user.getUsername())
               .setUserRoles(userRoles);
    }

    @Override
    public void logoutCurrentUser() {
        currentUser.setAnonymous(true);
    }
}
