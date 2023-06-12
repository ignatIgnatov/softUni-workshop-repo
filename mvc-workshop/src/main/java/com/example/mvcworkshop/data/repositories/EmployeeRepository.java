package com.example.mvcworkshop.data.repositories;

import com.example.mvcworkshop.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    Set<Employee> findAllByAgeGreaterThan(int age);
}
