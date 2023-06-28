package com.example.judgeV2.model.binding;

import javax.validation.constraints.Pattern;

public class HomeworkAddBindingModel {


    private String exercise;
    @Pattern(regexp = "https:\\/\\/github\\.com\\/.+", message = "Enter valid github address")
    private String gitAddress;

    public HomeworkAddBindingModel() {
    }

    public String getExercise() {
        return exercise;
    }

    public HomeworkAddBindingModel setExercise(String exercise) {
        this.exercise = exercise;
        return this;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public HomeworkAddBindingModel setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
        return this;
    }
}
