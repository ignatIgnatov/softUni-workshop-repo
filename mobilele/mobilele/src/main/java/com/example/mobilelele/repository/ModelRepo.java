package com.example.mobilelele.repository;

import com.example.mobilelele.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepo extends JpaRepository<ModelEntity, Long> {
    ModelEntity findByName(String model);
}
