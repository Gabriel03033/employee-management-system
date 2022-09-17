package com.project.it.employee;

import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    private final ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        if(!employees.isEmpty()) {
            employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
        }
        return employeeDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found with id: " + employeeId));
        EmployeeDto employeeDto = modelMapper.map(employee, new TypeToken<EmployeeDto>() {}.getType());
        return employeeDto;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, new TypeToken<Employee>() {}.getType());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, new TypeToken<EmployeeDto>() {}.getType());
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
        EmployeeDto updatedEmployeeDto = modelMapper.map(updatedEmployee, new TypeToken<EmployeeDto>() {}.getType());
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
