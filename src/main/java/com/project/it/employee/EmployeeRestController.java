package com.project.it.employee;

import java.awt.print.Pageable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(@RequestBody EmployeeDto employeeDto, @PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.updateEmployeeById(employeeDto, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Employee>> getEmployeesPerPage(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam String sortField, @RequestParam String sortDirection) {
        return new ResponseEntity<>(employeeService.getEmployeesPerPage(pageNumber, pageSize, sortField, sortDirection), HttpStatus.OK);
    }

    @GetMapping("/search/page")
    public ResponseEntity<Page<Employee>> getFilteredEmployeesByName(@RequestParam int pageSize, @RequestParam int pageNumber, @RequestParam String sortField, @RequestParam String sortDirection, @RequestParam String searchedName) {
        return new ResponseEntity<>(employeeService.getFilteredEmployeesByName(pageNumber, pageSize, sortField, sortDirection, searchedName), HttpStatus.OK);
    }
}
