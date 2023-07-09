package com.example.judgeV2.model.service;

import com.example.judgeV2.model.entity.RoleEntity;

public class UserServiceModel {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String git;
    private RoleEntity role;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public UserServiceModel setGit(String git) {
        this.git = git;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserServiceModel setRole(RoleEntity role) {
        this.role = role;
        return this;
    }
}
