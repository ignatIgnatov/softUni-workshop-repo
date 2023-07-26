package com.example.mobilele.mobilele.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // with this line we allow access to all static resources
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // the next line allows access to the home page and registration for everyone
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                // next we forbid all other pages for unauthenticated users
                .antMatchers("/**").authenticated()
                .and()
                // next configure login HTML form with two input fields
                .formLogin()
                // our login page is located at http://<serverAddress>:<port>/users/auth-login
                .loginPage("/users/login")
                // this is the name of the input in the login form where user enters her email/username etc...
                // the value of this input will be presented to our User detail service
                // those that want to name the input field differently, e.g. email may change the value
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                // the name of the <input...> HTML field that keeps the password
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                // The place where we should land in case that the login is successful
                .defaultSuccessUrl("/")
                // The place where we should land in case that the login is NOT successful
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                // This is the URL which spring will implement for me and will log the user out
                .logoutUrl("/users/logout")
                // where to go after logout
                .logoutSuccessUrl("/")
                // remove the session from the server
                .invalidateHttpSession(true)
                // remove the cookies that references my session
                .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // This gives spring two important components.
        // 1. Our service details that translates username/emails, phones, etc/ to userDetails
        // 2. Password encoder - the component that can decide if the user password matches

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);

        // topsecretpass -> password encoder -> asdfasldkjglaksdgsdgjsdf (hashed pwd)

        // login:
        // (username, raw_password) ->
        // password_encoder.matches(raw_password, hashed_pwd)
    }
}
