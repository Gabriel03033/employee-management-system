package com.project.it.employee;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String viewAllEmployeesPage(Model model) {
        return findAllEmployeesPagination(model, 5, 1);
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

    @GetMapping("/{employeeId}")
    public String viewEmployeeDetailsPage(Model model, @PathVariable Long employeeId) {
        model.addAttribute("employeeDto", employeeService.getEmployeeById(employeeId));
        return "employee-details";
    }

    @GetMapping("/page/{pageSize}/{pageNumber}")
    public String findAllEmployeesPagination(Model model, @PathVariable int pageSize, @PathVariable int pageNumber) {
        Page<Employee> employeesPage = employeeService.getAllEmployeesPagination(pageNumber, pageSize);
        List<Employee> employees = employeesPage.getContent();
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", employeesPage.getTotalPages());
        model.addAttribute("totalEmployees", employeesPage.getTotalElements());
        model.addAttribute("employees", employees);
        return "employees";
    }


}
