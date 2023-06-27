package com.example.mobilele.mobilele.security;

import com.example.mobilele.mobilele.model.entities.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private static final String ANONYMOUS_NAME = "anonymous";

    private String name = ANONYMOUS_NAME;
    private boolean isAnonymous = true;
    private List<UserRoleEnum> userRoles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public CurrentUser setUserRoles(List<UserRoleEnum> userRoles) {
        userRoles.clear();
        userRoles.addAll(userRoles);
        return this;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public boolean isLoggedIn() {
        return !isAnonymous;
    }

    public CurrentUser setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = ANONYMOUS_NAME;
            this.userRoles.clear();
        }
        isAnonymous = anonymous;
        return this;
    }
}
