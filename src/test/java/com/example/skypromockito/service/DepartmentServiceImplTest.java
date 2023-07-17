package com.example.skypromockito.service;

import com.example.skypromockito.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

public class DepartmentServiceImplTest {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        employeeService = Mockito.mock(EmployeeServiceImpl.class);
        departmentService = new DepartmentServiceImpl(employeeService);
    }

    @Test
    public void shouldReturnEmployeeWithMaxSalaryByFirstDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Employee result = departmentService.findMaxSalary(1);

        Assertions.assertEquals("Ivan", result.getFirstName());
        Assertions.assertEquals("Ivanov", result.getLastName());
        Assertions.assertEquals(1000, result.getSalary());

    }
    @Test
    public void shouldReturnEmployeeWithMaxSalaryBySecondDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Employee result = departmentService.findMaxSalary(2);

        Assertions.assertEquals("Boris", result.getFirstName());
        Assertions.assertEquals("Borisov", result.getLastName());
        Assertions.assertEquals(2000, result.getSalary());

    }
    @Test
    public void shouldReturnEmployeeWithMinSalaryByFirstDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Employee result = departmentService.findMinSalary(1);

        Assertions.assertEquals("Petr", result.getFirstName());
        Assertions.assertEquals("Petrov", result.getLastName());
        Assertions.assertEquals(800, result.getSalary());

    }
    @Test
    public void shouldReturnEmployeeWithMinSalaryBySecondDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Employee result = departmentService.findMinSalary(2);

        Assertions.assertEquals("Boris", result.getFirstName());
        Assertions.assertEquals("Borisov", result.getLastName());
        Assertions.assertEquals(2000, result.getSalary());

    }
    @Test
    public void shouldReturnSumSalaryByFirstDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Integer result = departmentService.findSalarySum(1);

        Assertions.assertEquals(1800,result);

    }

    @Test
    public void shouldReturnSumSalaryBySecondDepartment() {

        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());
        Integer result = departmentService.findSalarySum(2);

        Assertions.assertEquals(2000,result);

    }

    @Test
    public void shouldReturnEmployeeWhenDepartmentIs2() {
        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Collection<Employee> result = departmentService.findAllByDepartment(2);

        Assertions.assertEquals(1, result.size());

        Employee boris = result.stream().findFirst().orElseThrow();
        Assertions.assertEquals("Boris", boris.getFirstName());
        Assertions.assertEquals("Borisov", boris.getLastName());
    }
    @Test
    public void shouldReturnEmployeeWhenDepartmentIs1() {
        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Collection<Employee> result = departmentService.findAllByDepartment(1);

        Assertions.assertEquals(2, result.size());

        Employee ivan = result.stream().findFirst().orElseThrow();
        Assertions.assertEquals("Ivan", ivan.getFirstName());
        Assertions.assertEquals("Ivanov", ivan.getLastName());
    }

    @Test
    public void shouldReturnEmployeesGroupedByDepartment() {
        when(employeeService.getAllEmployees()).thenReturn(generateEmployee());

        Map<Integer, List<Employee>> result = departmentService.getGroupedByDepartments();

        Assertions.assertEquals(2, result.get(1).size());
        Assertions.assertEquals(1, result.get(2).size());
    }

    @Test
    public void shouldThrowExceptionWhenDepartmentIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentService.findAllByDepartment(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentService.findSalarySum(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentService.findMaxSalary(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> departmentService.findMinSalary(null));
    }
    // отдел не передан пользователем

    @Test
    public void shouldThrowExceptionWhenDepartmentDoesNotExists() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> departmentService.findAllByDepartment(3));
        Assertions.assertThrows(IllegalArgumentException.class,() -> departmentService.findMinSalary(3));
        Assertions.assertThrows(IllegalArgumentException.class,() -> departmentService.findMaxSalary(3));
        Assertions.assertThrows(IllegalArgumentException.class,() -> departmentService.findSalarySum(3));
    }
    //переданный отдел отсутствует

    private List<Employee> generateEmployee() {

        return List.of(new Employee("Ivan", "Ivanov", 1000, 1), new Employee("Petr", "Petrov", 800, 1), new Employee("Boris", "Borisov", 2000, 2));
    }
}
