package com.example.skypromockito.service;

import com.example.skypromockito.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    void addEmployee(Employee employee);

    void removeEmployee(Employee employee);

    Employee findEmployee(Employee employee);

    Collection<Employee> getAllEmployees();
}
