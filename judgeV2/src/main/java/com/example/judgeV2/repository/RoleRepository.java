package com.example.judgeV2.repository;

import com.example.judgeV2.model.entity.RoleEntity;
import com.example.judgeV2.model.entity.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleNameEnum roleNameEnum);
}
