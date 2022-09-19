package com.project.it.employee;

import com.project.it.dto.EmployeeDto;
import com.project.it.utils.EmployeeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerTest {

    @InjectMocks
    private EmployeeRestController employeeRestController;

    @Mock
    private EmployeeService employeeService;

    @Spy
    private ModelMapper modelMapper;

    private Employee employeeGeorgeBacalu;
    private Employee employeeGabrielFaur;
    private Employee employeeGabrielFirea;
    private Employee employeeAndreiMares;

    @BeforeEach
    void setUp() {
        employeeGeorgeBacalu = EmployeeUtils.getEmployeeGeorgeBacalu();
        employeeGabrielFaur = EmployeeUtils.getEmployeeGabrielFaur();
        employeeGabrielFirea = EmployeeUtils.getEmployeeGabrielFirea();
        employeeAndreiMares = EmployeeUtils.getEmployeeAndreiMares();
    }

    @Test
    void getAllEmployees_returnsEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeGeorgeBacalu);
        employees.add(employeeGabrielFaur);
        List<EmployeeDto> employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {
        }.getType());
        given(employeeService.getAllEmployees()).willReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> response = employeeRestController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDtos, response.getBody());
    }

    @Test
    void getEmployeeById_withValidEmployeeId_returnsEmployee() {
        EmployeeDto employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {
        }.getType());
        given(employeeService.getEmployeeById(1L)).willReturn(employeeGeorgeBacaluDto);
        ResponseEntity<EmployeeDto> response = employeeRestController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeGeorgeBacaluDto, response.getBody());
    }

    @Test
    void getEmployeeById_withInvalidEmployeeId_throwsException() {
        ResponseEntity<EmployeeDto> response = employeeRestController.getEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void addEmployee_shouldSucceed() {
        EmployeeDto employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {
        }.getType());
        given(employeeService.addEmployee(employeeGeorgeBacaluDto)).willReturn(employeeGeorgeBacaluDto);
        ResponseEntity<EmployeeDto> response = employeeRestController.addEmployee(employeeGeorgeBacaluDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeGeorgeBacaluDto, response.getBody());
    }

    @Test
    void updateEmployeeById_withValidEmployeeId_shouldSucceed() {
        employeeGabrielFaur.setEmployeeId(1L);
        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {
        }.getType());
        given(employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L)).willReturn(employeeGabrielFaurDto);
        ResponseEntity<EmployeeDto> response = employeeRestController.updateEmployeeById(employeeGabrielFaurDto, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeGabrielFaurDto, response.getBody());
    }

    @Test
    void updateEmployeeById_withInvalidEmployeeId_throwsException() {
        employeeGabrielFaur.setEmployeeId(1L);
        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {
        }.getType());
        ResponseEntity<EmployeeDto> response = employeeRestController.updateEmployeeById(employeeGabrielFaurDto, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteEmployeeById_shouldSucceed() {
        ResponseEntity<Map<String, String>> response = employeeRestController.deleteEmployeeById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Map.of("message", "Employee deleted successfully!"), response.getBody());
    }

    @Test
    void getEmployeesPerPage_shouldSucceed() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeGeorgeBacalu);
        employees.add(employeeGabrielFaur);
        employees.add(employeeGabrielFirea);
        employees.add(employeeAndreiMares);
        List<EmployeeDto> employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {
        }.getType());
        given(employeeService.getAllEmployees()).willReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> responseEmployeeDtos = employeeRestController.getAllEmployees();
        List<EmployeeDto> finalEmployeeDtos = Objects.requireNonNull(responseEmployeeDtos.getBody());

        List<EmployeeDto> firstPageEmployeeDtosContent = finalEmployeeDtos.subList(0, 1);
        Page<EmployeeDto> firstPageEmployeeDtos = new PageImpl<>(firstPageEmployeeDtosContent);
        List<EmployeeDto> secondPageEmployeeDtosContent = finalEmployeeDtos.subList(2, 3);
        Page<EmployeeDto> secondPageEmployeeDtos = new PageImpl<>(secondPageEmployeeDtosContent);

        given(employeeService.getEmployeesPerPage(1, 2, "name", "asc")).willReturn(firstPageEmployeeDtos);
        ResponseEntity<Page<EmployeeDto>> responsePage1 = employeeRestController.getEmployeesPerPage(2, 1, "name", "asc");
        given(employeeService.getEmployeesPerPage(2, 2, "name", "asc")).willReturn(secondPageEmployeeDtos);
        ResponseEntity<Page<EmployeeDto>> responsePage2 = employeeRestController.getEmployeesPerPage(2, 2, "name", "asc");

        assertEquals(HttpStatus.OK, responsePage1.getStatusCode());
        assertEquals(firstPageEmployeeDtosContent, Objects.requireNonNull(responsePage1.getBody()).getContent());
        assertEquals(HttpStatus.OK, responsePage2.getStatusCode());
        assertEquals(secondPageEmployeeDtosContent, Objects.requireNonNull(responsePage2.getBody()).getContent());
    }

    @Test
    void getFilteredEmployeesByName_shouldSucceed() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeGeorgeBacalu);
        employees.add(employeeGabrielFaur);
        employees.add(employeeGabrielFirea);
        employees.add(employeeAndreiMares);
        List<EmployeeDto> employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {
        }.getType());
        given(employeeService.getAllEmployees()).willReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> responseEmployeeDtos = employeeRestController.getAllEmployees();
        List<EmployeeDto> finalEmployeeDtos = Objects.requireNonNull(responseEmployeeDtos.getBody());

        List<EmployeeDto> firstPageEmployeeDtosContent = List.of(finalEmployeeDtos.get(1));
        Page<EmployeeDto> firstPageEmployeeDtos = new PageImpl<>(firstPageEmployeeDtosContent);
        List<EmployeeDto> secondPageEmployeeDtosContent = List.of(finalEmployeeDtos.get(2));
        Page<EmployeeDto> secondPageEmployeeDtos = new PageImpl<>(secondPageEmployeeDtosContent);

        given(employeeService.getFilteredEmployeesByName(1, 2, "name", "asc", "Gabriel")).willReturn(firstPageEmployeeDtos);
        ResponseEntity<Page<EmployeeDto>> responsePage1 = employeeRestController.getFilteredEmployeesByName(2, 1, "name", "asc", "Gabriel");
        given(employeeService.getFilteredEmployeesByName(2, 2, "name", "asc", "Gabriel")).willReturn(secondPageEmployeeDtos);
        ResponseEntity<Page<EmployeeDto>> responsePage2 = employeeRestController.getFilteredEmployeesByName(2, 2, "name", "asc", "Gabriel");

        assertEquals(HttpStatus.OK, responsePage1.getStatusCode());
        assertEquals(firstPageEmployeeDtosContent, Objects.requireNonNull(responsePage1.getBody()).getContent());
        assertEquals(HttpStatus.OK, responsePage2.getStatusCode());
        assertEquals(secondPageEmployeeDtosContent, Objects.requireNonNull(responsePage2.getBody()).getContent());
    }
}
