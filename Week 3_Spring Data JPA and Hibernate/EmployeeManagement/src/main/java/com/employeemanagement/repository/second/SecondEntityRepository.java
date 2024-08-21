package com.employeemanagementsystem.repository.second;

import com.employeemanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondEntityRepository extends JpaRepository<Department, Long> {
}
