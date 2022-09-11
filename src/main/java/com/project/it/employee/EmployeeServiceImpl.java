package com.project.it.employee;

import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MentorRepository mentorRepository;
    private final StudiesRepository studiesRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MentorRepository mentorRepository, StudiesRepository studiesRepository) {
        this.employeeRepository = employeeRepository;
        this.mentorRepository = mentorRepository;
        this.studiesRepository = studiesRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + employeeId));
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Long mentorId = employeeDto.getMentorId();
        Long studiesId = employeeDto.getStudiesId();

        Mentor mentorFromDto = mentorRepository.findById(mentorId).orElseThrow(() -> new ResourceNotFoundException("No mentor found with id: " + mentorId));
        Studies studiesFromDto = studiesRepository.findById(studiesId).orElseThrow(() -> new ResourceNotFoundException("No studies found with id: " + studiesId));

        Employee employee = Employee.builder()
                .employeeId(employeeDto.getEmployeeId())
                .name(employeeDto.getName())
                .email(employeeDto.getEmail())
                .password(employeeDto.getPassword())
                .mobile(employeeDto.getMobile())
                .address(employeeDto.getAddress())
                .birthday(employeeDto.getBirthday())
                .employeeType(employeeDto.getEmployeeType())
                .position(employeeDto.getPosition())
                .grade(employeeDto.getGrade())
                .mentor(mentorFromDto)
                .studies(studiesFromDto)
                .experiences(new ArrayList<>())
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeById(Employee employee, Long employeeId) {
        Employee employeeToUpdate = getEmployeeById(employeeId);
        employeeToUpdate.setName(employee.getName());
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
