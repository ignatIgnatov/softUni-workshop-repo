package com.example.mobilelele.repository;

import com.example.mobilelele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends JpaRepository<OfferEntity, Long> {
}
