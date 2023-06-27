package com.example.mvcworkshop.data.repositories;

import com.example.mvcworkshop.data.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String name);

    Set<Project> findAllByIsFinishedTrue();

}
