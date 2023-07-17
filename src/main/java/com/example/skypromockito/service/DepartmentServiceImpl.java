package com.example.skypromockito.service;

import com.example.skypromockito.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findMaxSalary(Integer department) {
        validateDept(department);
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                //.map(Employee::getSalary
                //.collect(Collectors.toList());
                //.max(Integer::compareTo)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("No employee"));
    }

    @Override  // найти сумму всех зарплат
    public Integer findSalarySum(Integer department) {
        validateDept(department);
        if (department == null) {
            throw new IllegalArgumentException();
        }
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Employee findMinSalary(Integer department) {
        validateDept(department);
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("No employee"));
    }

    @Override
    public Collection<Employee> findAllByDepartment(Integer department) {
        validateDept(department);
        if (department == null) {
            throw new IllegalArgumentException();
        }
        return employeeService.getAllEmployees()
                .stream()
                .filter(e -> Objects.equals(e.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getGroupedByDepartments() {
        return employeeService.getAllEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public void validateDept(Integer department) {
        List<Integer> departmentsList = employeeService.getAllEmployees()
                .stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toList());

        if (!departmentsList.contains(department)) {
            throw new IllegalArgumentException("No department");
        }
    }
}
