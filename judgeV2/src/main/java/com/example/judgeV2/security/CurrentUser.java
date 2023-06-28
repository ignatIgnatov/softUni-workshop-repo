package com.example.judgeV2.security;

import com.example.judgeV2.model.entity.RoleNameEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Long id;
    private String username;
    private RoleNameEnum role;

    public CurrentUser() {
    }

    public RoleNameEnum getRole() {
        return role;
    }

    public CurrentUser setRole(RoleNameEnum role) {
        this.role = role;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public boolean isAnonymous() {
        return this.username == null;
    }

    public boolean isAdmin() {
        return this.role == RoleNameEnum.ADMIN;
    }
}
