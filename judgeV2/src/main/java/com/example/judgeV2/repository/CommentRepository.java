package com.example.judgeV2.repository;

import com.example.judgeV2.model.entity.CommentEntity;
import com.example.judgeV2.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c.author FROM CommentEntity c ORDER BY c.score DESC")
    List<UserEntity> findAllTopScoredUsers();

    @Query("SELECT AVG(c.score) FROM CommentEntity c" )
    Double findAvgScore();
}
