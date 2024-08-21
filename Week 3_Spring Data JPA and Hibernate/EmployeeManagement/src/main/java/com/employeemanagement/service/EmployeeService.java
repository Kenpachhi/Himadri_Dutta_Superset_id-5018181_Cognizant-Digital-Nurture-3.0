package com.employeemanagementsystem.service;

import com.employeemanagementsystem.model.Employee;
import com.employeemanagementsystem.model.Department;
import com.employeemanagementsystem.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.employeemanagementsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
	
	
	

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public Department findDepartmentByName(String name) {
        return (Department) departmentRepository.findAll();
    }
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void saveEmployees(List<Employee> employees) {
        int batchSize = 50;
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
    
    public void updateEmployeeSalaries(double percentage) {
        String jpql = "UPDATE Employee e SET e.salary = e.salary * :percentage";
        entityManager.createQuery(jpql)
                     .setParameter("percentage", percentage)
                     .executeUpdate();
    }
}