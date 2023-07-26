package com.example.mobilelele.config;

import com.example.mobilelele.model.entity.enums.UserRoleEnum;
import com.example.mobilelele.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl UserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsServiceImpl mobileleleUserDetailsService, PasswordEncoder passwordEncoder) {
        this.UserDetailsService = mobileleleUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(UserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/home", "/users/login", "/users/register",
                        "/offers/all", "/brands/all").permitAll()
                // we permit the page only for admin users
                .antMatchers("/statistics").hasRole(UserRoleEnum.ADMIN.name())
//                .antMatchers("/offers/add").hasRole(UserRoleEnum.USER.name()) // only for...
                .anyRequest().authenticated() // everything else
                .and() // get the Login form
                .formLogin().loginPage("/users/login")
                .usernameParameter("username") // <input name=“username".. />
                .passwordParameter("password") // <input name=“password" .. />
                .defaultSuccessUrl("/")
                .failureForwardUrl("/users/login-error")
                .and() // get Logout
                .logout().logoutUrl("/users/logout") // <form action="@{/users/logout}" method="post">
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }
}
