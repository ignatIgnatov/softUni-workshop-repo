package com.example.patfinderd.service.impl;

import com.example.patfinderd.model.entity.User;
import com.example.patfinderd.model.entity.enums.LevelEnum;
import com.example.patfinderd.model.service.UserServiceModel;
import com.example.patfinderd.repository.UserRepository;
import com.example.patfinderd.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void loginUser(UserServiceModel userServiceModel) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(userServiceModel.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userServiceModel.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setLevel(LevelEnum.BEGINNER);

        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        if (passwordEncoder.matches(userServiceModel.getConfirmPassword(), user.getPassword())) {
            userRepository.save(user);
        } else {
            throw new UnsupportedOperationException("Enter valid password!");
        }

//        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(
//                userDetails,
//                user.getPassword(),
//                userDetails.getAuthorities()
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

    }


    @Override
    public UserServiceModel findById(Long id) {
        return userRepository
                .findById(id)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }
}
