package com.example.mobilele.mobilele.service;

public interface UserService {
    boolean authenticate(String username, String password);

    void loginUser(String username);
    void logoutCurrentUser();
}
