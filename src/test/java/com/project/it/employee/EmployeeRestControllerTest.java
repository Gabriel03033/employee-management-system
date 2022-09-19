package com.project.it.employee;

import com.project.it.dto.EmployeeDto;
import com.project.it.utils.EmployeeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

    @BeforeEach
    void setUp() {
        employeeGeorgeBacalu = EmployeeUtils.getEmployeeGeorgeBacalu();
        employeeGabrielFaur = EmployeeUtils.getEmployeeGabrielFaur();
    }

    @Test
    void getAllEmployees_returnsEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeGeorgeBacalu);
        employees.add(employeeGabrielFaur);
        List<EmployeeDto> employeeDtos = modelMapper.map(employees, new TypeToken<List<EmployeeDto>>() {}.getType());
        given(employeeService.getAllEmployees()).willReturn(employeeDtos);
        ResponseEntity<List<EmployeeDto>> response = employeeRestController.getAllEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDtos, response.getBody());
    }

    @Test
    void getEmployeeById_withValidEmployeeId_returnsEmployee() {
        EmployeeDto employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {}.getType());
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
        EmployeeDto employeeGeorgeBacaluDto = modelMapper.map(employeeGeorgeBacalu, new TypeToken<EmployeeDto>() {}.getType());
        given(employeeService.addEmployee(employeeGeorgeBacaluDto)).willReturn(employeeGeorgeBacaluDto);
        ResponseEntity<EmployeeDto> response = employeeRestController.addEmployee(employeeGeorgeBacaluDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeGeorgeBacaluDto, response.getBody());
    }

    @Test
    void updateEmployeeById_withValidEmployeeId_shouldSucceed() {
        employeeGabrielFaur.setEmployeeId(1L);
        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
        given(employeeService.updateEmployeeById(employeeGabrielFaurDto, 1L)).willReturn(employeeGabrielFaurDto);
        ResponseEntity<EmployeeDto> response = employeeRestController.updateEmployeeById(employeeGabrielFaurDto, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeGabrielFaurDto, response.getBody());
    }

    @Test
    void updateEmployeeById_withInvalidEmployeeId_throwsException() {
        employeeGabrielFaur.setEmployeeId(1L);
        EmployeeDto employeeGabrielFaurDto = modelMapper.map(employeeGabrielFaur, new TypeToken<EmployeeDto>() {}.getType());
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
}
