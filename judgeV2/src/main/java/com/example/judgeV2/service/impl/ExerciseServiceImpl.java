package com.example.judgeV2.service.impl;

import com.example.judgeV2.model.entity.ExerciseEntity;
import com.example.judgeV2.model.service.ExerciseServiceModel;
import com.example.judgeV2.repository.ExerciseRepository;
import com.example.judgeV2.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel exerciseServiceModel) {
        exerciseRepository.save(modelMapper.map(exerciseServiceModel, ExerciseEntity.class));
    }

    @Override
    public List<String> findAllExNames() {
        return exerciseRepository.findAllExNames();
    }

    @Override
    public boolean checkIfIsLate(String exercise) {
        ExerciseEntity exerciseEntity = exerciseRepository
                .findByName(exercise)
                .orElse(null);


        return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public ExerciseEntity findByName(String name) {
        return exerciseRepository.findByName(name).orElse(null);
    }
}
