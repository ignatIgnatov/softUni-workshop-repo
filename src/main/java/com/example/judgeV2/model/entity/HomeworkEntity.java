package com.example.judgeV2.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "homework")
public class HomeworkEntity extends BaseEntity {

    @Column(name = "added_on")
    private LocalDateTime addedOn;
    @Column(name = "git_address")
    private String gitAddress;
    @ManyToOne
    private UserEntity author;
    @ManyToOne
    private ExerciseEntity exercise;

    public HomeworkEntity() {
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public HomeworkEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkEntity setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public HomeworkEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public ExerciseEntity getExercise() {
        return exercise;
    }

    public HomeworkEntity setExercise(ExerciseEntity exercise) {
        this.exercise = exercise;
        return this;
    }
}
