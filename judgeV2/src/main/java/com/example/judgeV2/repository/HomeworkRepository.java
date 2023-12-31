package com.example.judgeV2.repository;

import com.example.judgeV2.model.entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Long> {

    Optional<HomeworkEntity> findTop1ByOrderByComments();
}
