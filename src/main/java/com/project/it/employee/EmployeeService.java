package com.project.it.employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployeeById(Employee employee, Long employeeId);

    void deleteEmployeeById(Long employeeId);
}
