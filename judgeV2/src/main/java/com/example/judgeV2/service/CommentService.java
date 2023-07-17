package com.example.judgeV2.service;

import com.example.judgeV2.model.entity.UserEntity;
import com.example.judgeV2.model.service.CommentServiceModel;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void add(CommentServiceModel serviceModel, Long homeworkId);

    List<UserEntity> findTopScoredUsers();

    Double findAvgScore();

    Map<Integer, Integer> findScoreMap();
}
