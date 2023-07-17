package com.example.skypromockito.service;

import com.example.skypromockito.exception.EmployeeAlreadyAddedException;
import com.example.skypromockito.exception.EmployeeNotFoundException;
import com.example.skypromockito.exception.UnexpectedCharacterException;
import com.example.skypromockito.model.Employee;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> storage = new HashMap<>();

    @Override
    public void addEmployee(Employee employee) {
        validate(employee.getFirstName(), employee.getLastName());
        employee.setFirstName(StringUtils.capitalize(employee.getFirstName().toLowerCase()));
        employee.setLastName(StringUtils.capitalize(employee.getLastName().toLowerCase()));

        if (storage.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeAlreadyAddedException("Employee has been already added");
        }
        storage.put(employee.getFirstName() + employee.getLastName(), employee);
    }

    @Override
    public void removeEmployee(Employee employee) {

        if (!storage.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeNotFoundException("Employee has not been found");
        }
        storage.remove(employee.getFirstName() + employee.getLastName(), employee);
    }
    @Override
    public Employee findEmployee(Employee employee) {
        if (!storage.containsKey(employee.getFirstName() + employee.getLastName())) {
            throw new EmployeeNotFoundException("Employee has not been found");
        }
        return storage.get(employee.getFirstName() + employee.getLastName());
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        return storage.values();
    }

    private void validate(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new UnexpectedCharacterException("Unexpected character in " + name);
            }
        }
    }
}
