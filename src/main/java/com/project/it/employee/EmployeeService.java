package com.project.it.employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployeeById(EmployeeDto employeeDto, Long employeeId);

    void deleteEmployeeById(Long employeeId);
}
