package com.project.it.service;

import com.project.it.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee saveEmployee(Employee employee);

    Employee updateEmployeeById(Employee employee, Long employeeId);

    void deleteEmployeeById(Long employeeId);
}
