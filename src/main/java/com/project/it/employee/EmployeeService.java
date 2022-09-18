package com.project.it.employee;

import com.project.it.dto.EmployeeDto;
import java.util.List;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long employeeId);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId);

    void deleteEmployeeById(Long employeeId);

    Page<Employee> getEmployeesPerPage(int pageNumber, int pageSize, String sortField, String sortDirection);

    Page<Employee> getFilteredEmployeesByName(int pageNumber, int pageSize, String sortField, String sortDirection, String searchedName);
}
