package com.example.skypromockito.controller;

import com.example.skypromockito.model.Employee;
import com.example.skypromockito.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public void addEmployee(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam int salary,
                            @RequestParam int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        employeeService.addEmployee(employee);
    }

    @GetMapping("/remove")
    public void removeEmployee(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam int salary,
                               @RequestParam int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        employeeService.removeEmployee(employee);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int salary,
                                 @RequestParam int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        return employeeService.findEmployee(employee);
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


}
