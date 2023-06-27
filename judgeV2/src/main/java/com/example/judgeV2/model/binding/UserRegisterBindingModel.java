package com.example.judgeV2.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {

    @Length(min = 2, message = "Username length must be minimum two characters")
    private String username;
    @Length(min = 3, message = "Password length must be minimum three characters")
    private String password;
    private String confirmPassword;
    @Email(message = "Enter valid email address")
    private String email;
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+", message = "Enter valid git address")
    private String git;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public UserRegisterBindingModel setGit(String git) {
        this.git = git;
        return this;
    }
}
