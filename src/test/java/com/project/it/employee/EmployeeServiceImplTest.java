package com.project.it.employee;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    private List<Employee> allEmployees;
    private Mentor mentorStefanPetrescu;
    private Studies studiesAutomatics;

    private EmployeeDto employeeGeorgeBacaluDto;
    private EmployeeDto employeeGabrielFaurDto;

    @BeforeEach
    void setUp() {
        employeeGeorgeBacalu = EmployeeUtils.getEmployeeGeorgeBacalu();
        employeeGabrielFaur = EmployeeUtils.getEmployeeGabrielFaur();
        allEmployees = EmployeeUtils.getAllEmployees();
        mentorStefanPetrescu = MentorUtils.getMentorStefanPetrescu();
        studiesAutomatics = StudiesUtils.getStudiesAutomatics();

        employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, EmployeeDto.class);
        employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, EmployeeDto.class);
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

        EmployeeDto result = employeeService.getEmployeeById(1L);
        assertEquals(employeeGeorgeBacaluDto, result);
    }

    @Test
    void getEmployeeById_withInvalidEmployeeId_throwException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
    }

    @Test
    void addEmployee_shouldSucceed() {
        given(employeeRepository.save(any())).willReturn(employeeGeorgeBacalu);

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
        given(employeeRepository.save(employeeGabrielFaur)).willReturn(employeeGabrielFaur);

        employeeGabrielFaurDto.setEmployeeId(1L);
        EmployeeDto result = employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L);
        assertEquals(employeeGabrielFaurDto, result);
    }

    @Test
    void updateEmployeeById_withInvalidEmployeeId_throwException() {
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No employee found with id: " + 1L, exception.getMessage());
    }

    @Test
    void updateEmployeeById_withInvalidMentorId_throwException() {
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L));
        assertEquals("No mentor found with id: " + 1L, exception.getMessage());
    }

    @Test
    void updateEmployeeById_withInvalidStudiesId_throwException() {
        Optional<Employee> employeeGeorgeBacaluOptional = Optional.ofNullable(employeeGeorgeBacalu);
        Optional<Mentor> mentorStefanPetrescuOptional = Optional.ofNullable(mentorStefanPetrescu);

        given(employeeRepository.findById(1L)).willReturn(employeeGeorgeBacaluOptional);
        given(mentorRepository.findById(1L)).willReturn(mentorStefanPetrescuOptional);

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

    @Test
    void getEmployeesPerPage_shouldReturnEmployeesOnPage() {
        given(employeeRepository.findAll()).willReturn(allEmployees);
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();

        Pageable firstPageTwoRecordsSortByNameAsc = PageRequest.of(0, 2, Sort.by("name").ascending());
        List<EmployeeDto> firstPageEmployeeDtosContent = employeeDtos.subList(0, 1);
        Page<EmployeeDto> firstPageEmployeeDtos = new PageImpl<>(firstPageEmployeeDtosContent);
        Page<Employee> firstPageEmployees = modelMapper.map(firstPageEmployeeDtos, new TypeToken<Page<Employee>>() {}.getType());

        Pageable secondPageOneRecordSortByNameAsc = PageRequest.of(1, 2, Sort.by("name").ascending());
        List<EmployeeDto> secondPageEmployeeDtosContent = employeeDtos.subList(2, 3);
        Page<EmployeeDto> secondPageEmployeeDtos = new PageImpl<>(secondPageEmployeeDtosContent);
        Page<Employee> secondPageEmployees = modelMapper.map(secondPageEmployeeDtos, new TypeToken<Page<Employee>>() {}.getType());

        given(employeeRepository.findAll(firstPageTwoRecordsSortByNameAsc)).willReturn(firstPageEmployees);
        Page<EmployeeDto> resultPage1 = employeeService.getEmployeesPerPage(1, 2, "name", "asc");
        given(employeeRepository.findAll(secondPageOneRecordSortByNameAsc)).willReturn(secondPageEmployees);
        Page<EmployeeDto> resultPage2 = employeeService.getEmployeesPerPage(2, 2, "name", "asc");

        assertEquals(firstPageEmployeeDtosContent, resultPage1.getContent());
        assertEquals(secondPageEmployeeDtosContent, resultPage2.getContent());
    }

    @Test
    void getFilteredEmployeesByName_shouldReturnFilteredEmployeeByNaeOnPage() {
        given(employeeRepository.findAll()).willReturn(allEmployees);
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();

        Pageable firstPageTwoRecordsSearchByName = PageRequest.of(0, 2);
        List<EmployeeDto> firstPageEmployeeDtosContent = List.of(employeeDtos.get(1));
        Page<EmployeeDto> firstPageEmployeeDtos = new PageImpl<>(firstPageEmployeeDtosContent);
        Page<Employee> firstPageEmployees = modelMapper.map(firstPageEmployeeDtos, new TypeToken<Page<Employee>>() {}.getType());

        Pageable secondPageOneRecordSearchByName = PageRequest.of(1, 2);
        List<EmployeeDto> secondPageEmployeeDtosContent = List.of(employeeDtos.get(2));
        Page<EmployeeDto> secondPageEmployeeDtos = new PageImpl<>(secondPageEmployeeDtosContent);
        Page<Employee> secondPageEmployees = modelMapper.map(secondPageEmployeeDtos, new TypeToken<Page<Employee>>() {}.getType());

        given(employeeRepository.findByNameContaining("Gabriel", firstPageTwoRecordsSearchByName)).willReturn(firstPageEmployees);
        Page<EmployeeDto> resultPage1 = employeeService.getFilteredEmployeesByName(1, 2, null, null, "Gabriel");
        given(employeeRepository.findByNameContaining("Gabriel", secondPageOneRecordSearchByName)).willReturn(secondPageEmployees);
        Page<EmployeeDto> resultPage2 = employeeService.getFilteredEmployeesByName(2, 2, null, null, "Gabriel");

        assertEquals(firstPageEmployeeDtosContent, resultPage1.getContent());
        assertEquals(secondPageEmployeeDtosContent, resultPage2.getContent());
    }
}
