package com.employeemanagementsystem.repository;

import com.employeemanagementsystem.dto.DepartmentDto;
import com.employeemanagementsystem.model.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("SELECT new com.employeemanagementsystem.dto.DepartmentDto(d.name) FROM Department d")
	List<DepartmentDto> findDepartmentDtos();
}
