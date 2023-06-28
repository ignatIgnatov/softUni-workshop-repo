package com.example.judgeV2.service;

import com.example.judgeV2.model.entity.ExerciseEntity;
import com.example.judgeV2.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {

    void addExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExNames();

    boolean checkIfIsLate(String exercise);

    ExerciseEntity findByName(String name);
}
