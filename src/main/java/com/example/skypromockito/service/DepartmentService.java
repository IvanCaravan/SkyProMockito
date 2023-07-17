package com.example.skypromockito.service;

import com.example.skypromockito.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxSalary(Integer department);

    Integer findSalarySum(Integer department);

    Employee findMinSalary(Integer department);

    Collection<Employee> findAllByDepartment(Integer department);

    Map<Integer, List<Employee>> getGroupedByDepartments();

}
