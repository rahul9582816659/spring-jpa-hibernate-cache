package com.hibernate.cache.api;

import com.hibernate.cache.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@Slf4j
public class EmployeeAPI {

    @PersistenceContext(unitName = "MySQL")
    private EntityManager entityManager;

    @PostMapping("/create-new-employee")
    @Transactional
    public Employee createNewEmployee(@RequestBody Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    @GetMapping("/find-employee/{id}")
    public Employee employee(@PathVariable Long id) {
        return entityManager.find(Employee.class, id);
    }

    @GetMapping("/find-employee-2/{id}")
    public Employee employee2(@PathVariable Long id) {
        return entityManager.find(Employee.class, id);
    }

    @PostMapping("/update-new-employee")
    @Transactional
    public Employee updateEmployee(@RequestBody Employee employee) {
        entityManager.merge(employee);
        return employee;
    }
}
