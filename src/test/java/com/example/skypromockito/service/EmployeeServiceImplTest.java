package com.example.skypromockito.service;

import com.example.skypromockito.exception.EmployeeAlreadyAddedException;
import com.example.skypromockito.exception.EmployeeNotFoundException;
import com.example.skypromockito.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeServiceImplTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }


    @Test
    public void employeeIsAddedAndFoundTest() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employee);
        Employee foundEmployee = employeeService.findEmployee(employee);
        Assertions.assertEquals(employee, foundEmployee);
    }
        //Поиск, когда есть
    @Test
    public void shouldAddEmployeeWhenDoesNotExist() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employee);
        Employee saved = employeeService.findEmployee(employee);

        Assertions.assertNotNull(saved);
        Assertions.assertEquals(employee.getSalary(), saved.getSalary());
        Assertions.assertEquals(employee.getDepartment(), saved.getDepartment());
    }
    //добавление, когда нет
    @Test
    public void shouldThrowExceptionWhenExists() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employee);

        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(employee));
    }
    //добавление, когда уже есть
    @Test
    public void shouldThrowExceptionWhenNothingToFind() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(employee));
    }
    // поиск, когда нет
    @Test
    public void shouldDeleteWhenExists() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);
        employeeService.addEmployee(employee);
        Employee foundEmployee = employeeService.findEmployee(employee);
        Assertions.assertEquals(employee, foundEmployee);

        employeeService.removeEmployee(employee);
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(employee));
    }
    //удаление, когда есть
    @Test
    public void shouldThrowExceptionWhenNothingToDelete() {
        Employee employee = new Employee("Ivan", "Ivanov", 1000, 1);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee(employee));
    }
    //удаление, когда нет

}
