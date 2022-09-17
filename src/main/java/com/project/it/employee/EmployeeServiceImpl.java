package com.project.it.employee;

import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MentorRepository mentorRepository;
    private final StudiesRepository studiesRepository;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        for(Employee employee : employees) {
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
            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
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
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
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
        
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeDto.builder()
                .employeeId(savedEmployee.getEmployeeId())
                .name(savedEmployee.getName())
                .email(savedEmployee.getEmail())
                .password(savedEmployee.getPassword())
                .mobile(savedEmployee.getMobile())
                .address(savedEmployee.getAddress())
                .birthday(savedEmployee.getBirthday())
                .employeeType(savedEmployee.getEmployeeType())
                .position(savedEmployee.getPosition())
                .grade(savedEmployee.getGrade())
                .mentorId(savedEmployee.getMentor().getMentorId())
                .studiesId(savedEmployee.getStudies().getStudiesId())
                .experiences(savedEmployee.getExperiences())
                .build();
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto updateEmployeeById(EmployeeDto employeeDto, Long employeeId) {
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
        
        Employee updatedEmployee = employeeRepository.save(employee);

        EmployeeDto updatedEmployeeDto = EmployeeDto.builder()
                .employeeId(updatedEmployee.getEmployeeId())
                .name(updatedEmployee.getName())
                .email(updatedEmployee.getEmail())
                .password(updatedEmployee.getPassword())
                .mobile(updatedEmployee.getMobile())
                .address(updatedEmployee.getAddress())
                .birthday(updatedEmployee.getBirthday())
                .employeeType(updatedEmployee.getEmployeeType())
                .position(updatedEmployee.getPosition())
                .grade(updatedEmployee.getGrade())
                .mentorId(updatedEmployee.getMentor().getMentorId())
                .studiesId(updatedEmployee.getStudies().getStudiesId())
                .experiences(updatedEmployee.getExperiences())
                .build();
        return updatedEmployeeDto;
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Page<Employee> getEmployeesPerPage(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Pageable pageable = getPageRequest(pageNumber, pageSize, sortField, sortDirection);
        Page<Employee> allEmployeesPage = employeeRepository.findAll(pageable);
        return allEmployeesPage;
    }

    @Override
    public Page<Employee> getFilteredEmployeesByName(int pageNumber, int pageSize, String sortField, String sortDirection, String searchedName) {
        Pageable pageable = getPageRequest(pageNumber, pageSize, sortField, sortDirection);
        Page<Employee> filteredEmployeesPage = employeeRepository.findByNameContaining(searchedName, pageable);
        return filteredEmployeesPage;
    }

    private Pageable getPageRequest(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sortByField = Sort.by(sortField);
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? sortByField.ascending() : sortByField.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return pageable;
    }
}

