package com.employeemanagementsystem.repository.primary;

import com.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstEntityRepository extends JpaRepository<Employee, Long> {
}
