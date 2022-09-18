package com.project.it.employee;

import com.project.it.dto.EmployeeDto;
import com.project.it.employee.enums.Grade;
import com.project.it.employee.enums.JobType;
import com.project.it.employee.enums.Position;
import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private MentorRepository mentorRepository;
    @Mock
    private StudiesRepository studiesRepository;

    @Spy
    private ModelMapper modelMapper;

    Mentor mentorStefanPetrescu = Mentor.builder()
            .mentorId(1L)
            .name("Stefan Petrescu")
            .email("stefanpetrescu@email.com")
            .password("stefanpetrescu")
            .mobile("1000000001")
            .address("mentor_address1")
            .birthday(LocalDate.of(1997, 1, 1))
            .available(true)
            .numberOfEmployees(5)
            .build();

    Studies studiesAutomatics = Studies.builder()
            .studiesId(1L)
            .university("University of Transylvania")
            .faculty("Faculty of Electrical Engineering and Computer Science")
            .major("Automatic and Applied Computer Science")
            .build();

    Employee employeeGeorgeBacalu = Employee.builder()
            .employeeId(1L)
            .name("George Bacalu")
            .email("georgebacalu@email.com")
            .password("georgebacalu")
            .mobile("0000000001")
            .address("employee_address1")
            .birthday(LocalDate.of(2001, 3, 8))
            .jobType(JobType.FULL_TIME)
            .position(Position.BACKEND)
            .grade(Grade.JUNIOR)
            .mentor(mentorStefanPetrescu)
            .studies(studiesAutomatics)
            .experiences(new ArrayList<>())
            .build();

    Employee employeeGabrielFaur = Employee.builder()
            .employeeId(2L)
            .name("Gabriel Faur")
            .email("gabrielfaur@email.com")
            .password("gabrielfaur")
            .mobile("0000000002")
            .address("employee_address2")
            .birthday(LocalDate.of(2003, 1, 20))
            .jobType(JobType.FULL_TIME)
            .position(Position.BACKEND)
            .grade(Grade.JUNIOR)
            .mentor(mentorStefanPetrescu)
            .studies(studiesAutomatics)
            .experiences(new ArrayList<>())
            .build();

    @Test
    void getAllEmployees_withPopulatedListOfEmployees_returnsEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeGeorgeBacalu);
        employees.add(employeeGabrielFaur);
        given(employeeRepository.findAll()).willReturn(employees);

        List<EmployeeDto> employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
        List<EmployeeDto> result = employeeService.getAllEmployees();

        assertEquals(employeeDtos, result);
    }

    @Test
    void getAllEmployees_withEmptyListOfEmployees_returnsEmptyList() {
        List<EmployeeDto> result = employeeService.getAllEmployees();
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void getEmployeeById_withValidEmployeeId_returnsEmployee() {
        Optional<Employee> employee = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employee);

        EmployeeDto employeeDto = modelMapper.map(employee, new TypeToken<EmployeeDto>() {}.getType());
        EmployeeDto result = employeeService.getEmployeeById(1L);
        assertEquals(employeeDto, result);
    }

    @Test
    void getEmployeeById_withInvalidEmployeeId_throwException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
    }

    @Test
    void addEmployee_shouldSucceed() {
        given(employeeRepository.save(any())).willReturn(employeeGeorgeBacalu);

        EmployeeDto employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {}.getType());
        EmployeeDto result = employeeService.addEmployee(employeeGeorgeBacaluDto);
        assertEquals(employeeGeorgeBacaluDto, result);
    }

    @Test
    void updateEmployeeById_shouldSucceed() {
        Optional<Employee> employee1 = Optional.ofNullable(employeeGeorgeBacalu);
        Optional<Mentor> mentor = Optional.ofNullable(mentorStefanPetrescu);
        Optional<Studies> studies = Optional.ofNullable(studiesAutomatics);

        given(employeeRepository.findById(1L)).willReturn(employee1);
        given(mentorRepository.findById(1L)).willReturn(mentor);
        given(studiesRepository.findById(1L)).willReturn(studies);

        employeeGabrielFaur.setEmployeeId(1L);
        given(employeeRepository.save(any())).willReturn(employeeGabrielFaur);

        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        EmployeeDto result = employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L);
        assertEquals(employeeGabrielFaurDto, result);
    }

    @Test
    void updateEmployeeById_withInvalidEmployeeId_throwException() {
        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
    }

    @Test
    void updateEmployeeById_withInvalidMentorId_throwException() {
        Optional<Employee> employee1 = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employee1);

        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No mentor found with id: " + 1L, exception.getMessage());
    }

    @Test
    void updateEmployeeById_withInvalidStudiesId_throwException() {
        Optional<Employee> employee1 = Optional.ofNullable(employeeGeorgeBacalu);
        Optional<Mentor> mentor = Optional.ofNullable(mentorStefanPetrescu);

        given(employeeRepository.findById(1L)).willReturn(employee1);
        given(mentorRepository.findById(1L)).willReturn(mentor);

        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No studies found with id: " + 1L, exception.getMessage());
    }

    @Test
    void deleteEmployeeById_withValidEmployeeId_shouldSucceed() {
        Optional<Employee> employee1 = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employee1);

        employeeService.deleteEmployeeById(1L);
    }

    @Test
    void deleteEmployeeById_withInvalidEmployeeId_throwException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployeeById(1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
    }
}