package com.project.it.employee;

import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + employeeId));
        EmployeeDto employeeDto = EmployeeDto.builder()
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .email(employee.getEmail())
                .password(employee.getPassword())
                .mobile(employee.getMobile())
                .address(employee.getAddress())
                .birthday(employee.getBirthday())
                .employeeType(employee.getEmployeeType())
                .position(employee.getPosition())
                .grade(employee.getGrade())
                .mentorId(employee.getMentor().getMentorId())
                .studiesId(employee.getStudies().getStudiesId())
                .experiences(employee.getExperiences())
                .build();
        return employeeDto;
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        log.info("mentorId: {}", employeeDto.getMentorId());
        log.info("studiesId: {}", employeeDto.getStudiesId());
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
    public Employee updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + employeeId));

        Long mentorId = employeeDto.getMentorId();
        Long studiesId = employeeDto.getStudiesId();

        Mentor mentorFromDto = mentorRepository.findById(mentorId).orElseThrow(() -> new ResourceNotFoundException("No mentor found with id: " + mentorId));
        Studies studiesFromDto = studiesRepository.findById(studiesId).orElseThrow(() -> new ResourceNotFoundException("No studies found with id: " + studiesId));

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(employeeDto.getPassword());
        employee.setMobile(employeeDto.getMobile());
        employee.setAddress(employeeDto.getAddress());
        employee.setBirthday(employeeDto.getBirthday());
        employee.setEmployeeType(employeeDto.getEmployeeType());
        employee.setPosition(employeeDto.getPosition());
        employee.setGrade(employeeDto.getGrade());
        employee.setMentor(mentorFromDto);
        employee.setStudies(studiesFromDto);
        employee.setExperiences(employeeDto.getExperiences());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
