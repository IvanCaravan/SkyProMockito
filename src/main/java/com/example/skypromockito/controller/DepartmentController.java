package com.example.skypromockito.controller;

import com.example.skypromockito.model.Employee;
import com.example.skypromockito.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(value = "/{Id}/employees")
    public Collection<Employee> getByDepartment(@PathVariable Integer Id) {
        return departmentService.findAllByDepartment(Id);
    }

    @GetMapping(value = "/{Id}/salary/sum")
    public Integer getSalarySumByDepartment(@PathVariable (value = "Id") Integer departmentId) {
        return departmentService.findSalarySum(departmentId);
    }

    @GetMapping(value = "/{Id}/salary/max")
    public Employee getMaxSalaryByDepartment(@PathVariable (value = "Id") Integer departmentId) {
        return departmentService.findMaxSalary(departmentId);
    }
    @GetMapping(value = "/{Id}/salary/min")
    public Employee getMinSalaryByDepartment(@PathVariable (value = "Id") Integer departmentId) {
        return departmentService.findMinSalary(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupedByDepartment() {
        return departmentService.getGroupedByDepartments();
    }



}
