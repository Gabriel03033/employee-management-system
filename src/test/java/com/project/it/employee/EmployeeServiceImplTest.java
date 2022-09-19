package com.project.it.employee;

import com.project.it.dto.EmployeeDto;
import com.project.it.exception.ResourceNotFoundException;
import com.project.it.mentor.Mentor;
import com.project.it.mentor.MentorRepository;
import com.project.it.studies.Studies;
import com.project.it.studies.StudiesRepository;
import com.project.it.utils.EmployeeUtils;
import com.project.it.utils.MentorUtils;
import com.project.it.utils.StudiesUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    private Employee employeeGeorgeBacalu;
    private Employee employeeGabrielFaur;
    private Mentor mentorStefanPetrescu;
    private Studies studiesAutomatics;

    @BeforeEach
    void setUp() {
        employeeGeorgeBacalu = EmployeeUtils.getEmployeeGeorgeBacalu();
        employeeGabrielFaur = EmployeeUtils.getEmployeeGabrielFaur();
        mentorStefanPetrescu = MentorUtils.getMentorStefanPetrescu();
        studiesAutomatics = StudiesUtils.getStudiesAutomatics();
    }

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
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);

        EmployeeDto employeeDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {}.getType());
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
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        Optional<Mentor> mentorStefanPetrescuOptional = Optional.ofNullable(mentorStefanPetrescu);
        Optional<Studies> studiesAutomaticsOptional = Optional.ofNullable(studiesAutomatics);

        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);
        given(mentorRepository.findById(1L)).willReturn(mentorStefanPetrescuOptional);
        given(studiesRepository.findById(1L)).willReturn(studiesAutomaticsOptional);

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
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);

        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No mentor found with id: " + 1L, exception.getMessage());
    }

    @Test
    void updateEmployeeById_withInvalidStudiesId_throwException() {
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        Optional<Mentor> mentorStefanPetrescuOptional = Optional.ofNullable(mentorStefanPetrescu);

        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);
        given(mentorRepository.findById(1L)).willReturn(mentorStefanPetrescuOptional);

        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No studies found with id: " + 1L, exception.getMessage());
    }

    @Test
    void deleteEmployeeById_withValidEmployeeId_shouldSucceed() {
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);

        employeeService.deleteEmployeeById(1L);
        verify(employeeRepository, times(1)).delete(employeeGeorgeBacalu);
    }

    @Test
    void deleteEmployeeById_withInvalidEmployeeId_throwException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployeeById(1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
        verify(employeeRepository, never()).delete(employeeGeorgeBacalu);
    }
}
