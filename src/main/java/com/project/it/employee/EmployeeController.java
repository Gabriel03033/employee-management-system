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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public String viewAllEmployeesPage(Model model) {
        return findEmployeesPagination(model, 6, 1, "name", "asc", "");
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

    @RequestMapping(value = "/delete-employee/{employeeId}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/{employeeId}")
    public String viewEmployeeDetailsPage(Model model, @PathVariable Long employeeId) {
        model.addAttribute("employeeDto", employeeService.getEmployeeById(employeeId));
        return "employee-details";
    }

    @GetMapping("/page")
    public String findEmployeesPagination(Model model,
                                          @RequestParam int pageSize, @RequestParam int pageNumber,
                                          @RequestParam String sortField, @RequestParam String sortDirection,
                                          @ModelAttribute @RequestParam(required = false) String searchedName) {
        Page<Employee> filteredEmployeesPage = employeeService.getFilteredEmployeesByName(pageNumber, pageSize, sortField, sortDirection, searchedName);
        Page<Employee> allEmployeesPage = employeeService.getEmployeesPerPage(pageNumber, pageSize, sortField, sortDirection);
        List<Employee> filteredEmployeesPageContent = filteredEmployeesPage.getContent();
        List<Employee> allEmployeesPageContent = allEmployeesPage.getContent();
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("switchedSortDirection", sortDirection.equalsIgnoreCase("asc") ? "desc" : "asc");
        if (searchedName == null) {
            model.addAttribute("totalPages", allEmployeesPage.getTotalPages());
            model.addAttribute("totalEmployees", allEmployeesPage.getTotalElements());
            model.addAttribute("employees", allEmployeesPageContent);
            model.addAttribute("searchedName", "");
        } else {
            model.addAttribute("totalPages", filteredEmployeesPage.getTotalPages());
            model.addAttribute("totalEmployees", filteredEmployeesPage.getTotalElements());
            model.addAttribute("employees", filteredEmployeesPageContent);
            model.addAttribute("searchedName", searchedName);
        }
        return "employees";
    }
}
