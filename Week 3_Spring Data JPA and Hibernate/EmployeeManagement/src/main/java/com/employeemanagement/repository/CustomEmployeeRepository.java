package com.employeemanagementsystem.repository;

import com.employeemanagementsystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomEmployeeRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Employee> findEmployeesByName(String name) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByName", Employee.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByDepartmentName", Employee.class);
        query.setParameter("departmentName", departmentName);
        return query.getResultList();
    }
}