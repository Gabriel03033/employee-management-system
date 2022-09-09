package com.project.it.service.Impl;

import com.project.it.entity.Employee;
import com.project.it.repository.EmployeeRepository;
import com.project.it.service.EmployeeService;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) { this.employeeRepository = employeeRepository;}


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();}

    @Override
    public Employee getEmployeesById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("No employees foud with id: " + employeeId));}

    @Override
    public Employee saveEmplyees(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeesById(Employee employee, Long employeeId) {
        Employee employeeToUpdate = getEmployeesById(employeeId);
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
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeesById(Long employeeId) {

    }
}
