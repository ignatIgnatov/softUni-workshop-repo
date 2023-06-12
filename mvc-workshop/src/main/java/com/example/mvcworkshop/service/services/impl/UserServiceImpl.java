package com.example.mvcworkshop.service.services.impl;

import com.example.mvcworkshop.data.entities.Role;
import com.example.mvcworkshop.data.entities.User;
import com.example.mvcworkshop.data.repositories.RoleRepository;
import com.example.mvcworkshop.data.repositories.UserRepository;
import com.example.mvcworkshop.service.models.UserServiceModel;
import com.example.mvcworkshop.service.services.RoleService;
import com.example.mvcworkshop.service.services.UserService;
import com.example.mvcworkshop.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {

        User user = this.modelMapper.map(userRegisterModel, User.class);

        if (this.userRepository.count() == 0) {
            //seedAllRoles
            this.roleService.seedRolesInDb();

            //set roles ADMIN and USER to user
//            user.setAuthorities(this.roleService.findAllRoles()
//                    .stream()
//                    .map(r -> this.modelMapper.map(r, Role.class))
//                    .collect(Collectors.toSet()));
            user.setAuthorities(new LinkedHashSet<>());
            user.getAuthorities().add(this.roleRepository.findByAuthority("ADMIN"));
            user.getAuthorities().add(this.roleRepository.findByAuthority("USER"));

        } else {
            //set to user USER-role
            user.setAuthorities(new LinkedHashSet<>());
            user.getAuthorities().add(this.roleRepository.findByAuthority("USER"));
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterModel.getPassword()));

        return this.modelMapper.map(this.userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s);
    }
}
