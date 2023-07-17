package com.example.judgeV2.service;

import com.example.judgeV2.model.entity.HomeworkEntity;
import com.example.judgeV2.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void addHomework(String exercise, String gitAddress);

    HomeworkServiceModel findHomeworkForScoring();

    HomeworkEntity findById(Long homeworkId);
}
