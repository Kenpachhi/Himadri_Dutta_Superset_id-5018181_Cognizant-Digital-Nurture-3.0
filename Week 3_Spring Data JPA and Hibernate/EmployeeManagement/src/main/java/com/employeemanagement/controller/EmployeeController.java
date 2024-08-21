package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/by-name")
    public Page<Employee> getEmployeesByName(
            @RequestParam String name, 
            Pageable pageable) {
        return employeeRepository.findByName(name, pageable);
    }

    @GetMapping("/by-email")
    public Page<Employee> getEmployeeByEmail(
            @RequestParam String email, 
            Pageable pageable) {
        return employeeRepository.findByEmail(email, pageable);
    }

    @GetMapping("/by-department")
    public Page<Employee> getEmployeesByDepartmentName(
            @RequestParam String departmentName, 
            Pageable pageable) {
        return employeeRepository.findEmployeesByDepartmentName(departmentName, pageable);
    }

    @GetMapping("/search")
    public Page<Employee> getEmployeesByNameContaining(
            @RequestParam String keyword, 
            Pageable pageable) {
        return employeeRepository.findEmployeesByNameContaining(keyword, pageable);
    }
}
