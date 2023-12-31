package com.example.judgeV2.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "git", nullable = false)
    private String git;
    @ManyToOne
    private RoleEntity role;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<HomeworkEntity> homeworkEntitySet;

    public UserEntity() {
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGit() {
        return git;
    }

    public UserEntity setGit(String git) {
        this.git = git;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(RoleEntity role) {
        this.role = role;
        return this;
    }

    public Set<HomeworkEntity> getHomeworkEntitySet() {
        return homeworkEntitySet;
    }

    public UserEntity setHomeworkEntitySet(Set<HomeworkEntity> homeworkEntitySet) {
        this.homeworkEntitySet = homeworkEntitySet;
        return this;
    }
}
