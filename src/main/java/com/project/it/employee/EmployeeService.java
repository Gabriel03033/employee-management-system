package com.project.it.employee;

import java.util.List;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId);

    void deleteEmployeeById(Long employeeId);

    Page<EmployeeDto> getEmployeesPerPage(int pageNumber, int pageSize, String sortField, String sortDirection);

    Page<EmployeeDto> getFilteredEmployeesByName(int pageNumber, int pageSize, String sortField, String sortDirection, String searchedName);
}
