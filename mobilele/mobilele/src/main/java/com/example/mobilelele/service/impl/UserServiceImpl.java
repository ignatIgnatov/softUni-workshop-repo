package com.example.mobilelele.service.impl;

import com.example.mobilelele.model.entity.UserEntity;
import com.example.mobilelele.model.entity.UserRoleEntity;
import com.example.mobilelele.model.entity.enums.UserRoleEnum;
import com.example.mobilelele.model.service.UserRegisterServiceModel;
import com.example.mobilelele.repository.UserRepo;
import com.example.mobilelele.repository.UserRoleRepo;
import com.example.mobilelele.security.UserDetailsServiceImpl;
import com.example.mobilelele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserRoleRepo userRoleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserDetailsServiceImpl userDetailsService;

    public UserServiceImpl(UserRepo userRepo, UserRoleRepo userRoleRepo, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userDetailsService = userDetailsService;
    }

//    @Override  // return true, if user authenticated successfully
//    public boolean authenticate(String username, String password) {
//        Optional<UserEntity> userOpt = userRepo.findByUsername(username);
//        if (userOpt.isEmpty()){
//            return false;
//        }
//        return passwordEncoder.matches(password, userOpt.get().getPassword());
//    }
//
//    @Override  // set CurrentUser to the Logged-In user
//    public void loginUser(String username) {
//        final UserEntity user = userRepo.findByUsername(username).orElseThrow();
//        final List<Role> userRoles = user.getRoles().stream()
//                .map(UserRole::getName)
//                .collect(Collectors.toList());
//
//        currentUser.setName(username);
//        currentUser.setUserRoleList(userRoles);
//        currentUser.setAnonymous(false);
//    }
//
//    @Override
//    public void logoutUser() {
//        currentUser.setAnonymous(true);
//    }

    @Override
    public boolean checkUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    @Override
    public void registerAndLogin(UserRegisterServiceModel userRegisterServiceModel) {
        UserEntity userEntity = new UserEntity();
        UserRoleEntity userRole = userRoleRepo.findByName(UserRoleEnum.USER);
        userEntity.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        userEntity.setRoles(List.of(userRole));
        userEntity.setFirstName(userRegisterServiceModel.getFirstName());
        userEntity.setLastName(userRegisterServiceModel.getLastName());
        userEntity.setUsername(userRegisterServiceModel.getUsername());
        userEntity.setActive(true);
        userEntity = userRepo.save(userEntity);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userEntity.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserEntity getUserByName(String name) {
        return userRepo.findByUsername(name).orElse(null);
    }
}
