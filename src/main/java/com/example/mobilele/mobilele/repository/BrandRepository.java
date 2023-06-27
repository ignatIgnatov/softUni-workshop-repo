package com.example.mobilele.mobilele.repository;

import com.example.mobilele.mobilele.model.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
