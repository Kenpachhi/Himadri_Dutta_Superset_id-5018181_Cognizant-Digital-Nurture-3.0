package com.employeemanagementsystem.repository;

import com.employeemanagementsystem.dto.EmployeeDto;
import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.projection.CustomEmployeeProjection;
import com.employeemanagementsystem.projection.EmployeeProjection;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Page<Employee> findByName(String name, Pageable pageable);

	Page<Employee> findByEmail(String email, Pageable pageable);

	Page<Employee> findEmployeesByDepartmentName(String departmentName, Pageable pageable);

	Page<Employee> findEmployeesByNameContaining(String keyword, Pageable pageable);

	List<Employee> findByDepartmentName(String departmentName);

	List<EmployeeProjection> findBy();

	@Query("SELECT new com.employeemanagementsystem.dto.EmployeeDto(e.name, e.email) FROM Employee e")
	List<EmployeeDto> findEmployeeDtos();
	
	@Query("SELECT e FROM Employee e")
    List<CustomEmployeeProjection> findCustomEmployeeProjections();
}
