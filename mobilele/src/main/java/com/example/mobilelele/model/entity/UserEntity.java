package com.example.mobilelele.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

    @Column(name = "image_url")
    private String imageUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEntity> role) {
        this.roles = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password=****" +'\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isActive=" + isActive +
                ", role=" + roles +
                ", imageUrl='" + imageUrl + '\'' +
                "} " + super.toString();
    }
}
