package com.project.it.service;

import com.project.it.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeesById(Long employeeId);
    Employee saveEmplyees(Employee employee);
    Employee updateEmployeesById(Employee employee, Long employeeId);
    void deleteEmployeesById(Long employeeId);
}
