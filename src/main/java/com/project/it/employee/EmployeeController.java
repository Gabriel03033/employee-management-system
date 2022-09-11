package com.project.it.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String viewAllEmployeesPage(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/save-employee")
    public String viewSaveEmployeeFormPage(Model model) {
        model.addAttribute("employeeDto", new EmployeeDto());
        return "save-employee";
    }

    @PostMapping("/save-employee")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto) {
        employeeService.saveEmployee(employeeDto);
        return "redirect:/employees";
    }

    @GetMapping("/update-employee/{employeeId}")
    public String viewUpdateEmployeeFormPage(Model model, @PathVariable Long employeeId) {
        model.addAttribute("employeeDto", employeeService.getEmployeeById(employeeId));
        return "update-employee";
    }

    @PostMapping("/update-employee/{employeeId}")
    public String updateEmployee(@ModelAttribute EmployeeDto employeeDto, @PathVariable Long employeeId) {
        employeeService.updateEmployeeById(employeeDto, employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/delete-employee/{employeeId}")
    public String deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees";
    }
}
