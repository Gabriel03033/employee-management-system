package com.project.it.service.Impl;

import com.project.it.entity.Employee;
import com.project.it.repository.EmployeeRepository;
import com.project.it.service.EmployeeService;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("No employees found with id: " + employeeId));
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Employee employee, Long employeeId) {
        Employee employeeToUpdate = getEmployeeById(employeeId);
        employeeToUpdate.setFirstName(employee.getFirstName());
        employee.setLastName(employee.getLastName());
        employee.setEmail(employee.getEmail());
        employee.setPassword(employee.getPassword());
        employee.setMobile(employee.getMobile());
        employee.setAddress(employee.getAddress());
        employee.setBirthday(employee.getBirthday());
        employee.setEmployeeType(employee.getEmployeeType());
        employee.setPosition(employee.getPosition());
        employee.setGrade(employee.getGrade());
        employee.setMentor(employee.getMentor());
        employee.setStudies(employee.getStudies());
        employee.setExperiences(employee.getExperiences());
        return employeeRepository.save(employeeToUpdate);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
