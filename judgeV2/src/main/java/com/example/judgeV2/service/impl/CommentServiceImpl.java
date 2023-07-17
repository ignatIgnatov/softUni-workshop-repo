package com.example.judgeV2.service.impl;

import com.example.judgeV2.model.entity.CommentEntity;
import com.example.judgeV2.model.entity.UserEntity;
import com.example.judgeV2.model.service.CommentServiceModel;
import com.example.judgeV2.repository.CommentRepository;
import com.example.judgeV2.security.CurrentUser;
import com.example.judgeV2.service.CommentService;
import com.example.judgeV2.service.HomeworkService;
import com.example.judgeV2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final HomeworkService homeworkService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper, UserService userService, CurrentUser currentUser, HomeworkService homeworkServic) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUser = currentUser;
        this.homeworkService = homeworkServic;
    }

    @Override
    public void add(CommentServiceModel serviceModel, Long homeworkId) {
        CommentEntity comment = modelMapper
                .map(serviceModel, CommentEntity.class);
        comment.setAuthor(userService.findById(currentUser.getId()));
        comment.setHomework(homeworkService.findById(homeworkId));


        commentRepository.save(comment);
    }

    @Override
    public List<UserEntity> findTopScoredUsers() {
        return commentRepository.findAllTopScoredUsers().stream().limit(4).collect(Collectors.toList());
    }

    @Override
    public Double findAvgScore() {
        return commentRepository.findAvgScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initScoreMap();

        commentRepository
                .findAll()
                .forEach(comment -> {
                    Integer score = comment.getScore();
                    scoreMap.put(score, scoreMap.get(score) + 1);
                });

        return scoreMap;
    }

    private Map<Integer, Integer> initScoreMap() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= 6; i++) {
            map.put(i, 0);
        }
        return map;
    }


}
