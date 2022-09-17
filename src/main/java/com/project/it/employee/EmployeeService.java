package com.project.it.employee;

import java.util.List;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId);

    void deleteEmployeeById(Long employeeId);

    Page<Employee> getEmployeesPerPage(int pageNumber, int pageSize, String sortField, String sortDirection);

    Page<Employee> getFilteredEmployeesByName(int pageNumber, int pageSize, String sortField, String sortDirection, String searchedName);
}
