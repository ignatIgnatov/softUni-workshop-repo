package com.example.mobilele.mobilele.service.impl;

import com.example.mobilele.mobilele.model.entities.UserEntity;
import com.example.mobilele.mobilele.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileleUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public MobileleUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // The purpose of this method is to map user representation (UserEntity)
        // to the user representation in the spring security world (UserDetails)
        // The only thing that spring will provide to us is the username.
        // The username will come from the HTML login form.

       UserEntity userEntity = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not fund!"));

        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        // GrantedAuthority is the representation of a user role in the spring world.
        // SimpleGrandAuthority is an implementation of GrantedAuthority
        // which spring provides for our convenience.
        // Our representation of role is UserRoleEntity

        List<GrantedAuthority> authorities = userEntity.getUserRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());

        // User in the spring implementation of UserDetails interface.

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
