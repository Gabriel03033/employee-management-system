package com.project.it.employee;

import com.project.it.utils.SearchDto;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String viewAllEmployeesPage(Model model) {
        return findEmployeesPagination(model, 6, 1, "", "", "");
    }

    @GetMapping("/save-employee/{employeeId}")
    public String viewSaveEmployeeFormPage(Model model, @PathVariable Long employeeId) {
        model.addAttribute("employeeId", employeeId);
        if(employeeId == -1) {
            model.addAttribute("employeeDto", new EmployeeDto());
        } else {
            model.addAttribute("employeeDto", employeeService.getEmployeeById(employeeId));
        }
        return "save-employee";
    }

    @PostMapping("/save-employee/{employeeId}")
    public String saveEmployee(@ModelAttribute EmployeeDto employeeDto, @PathVariable Long employeeId) {
        if(employeeId == -1) {
            employeeService.addEmployee(employeeDto);
        } else {
            employeeService.updateEmployeeById(employeeDto, employeeId);
        }
        return "redirect:/employees";
    }

    @DeleteMapping("/delete-employee/{employeeId}")
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
                                          @RequestParam String searchedName) {
        Page<EmployeeDto> filteredEmployeesPage = employeeService.getFilteredEmployeesByName(pageNumber, pageSize, sortField, sortDirection, searchedName);
        Page<EmployeeDto> allEmployeesPage = employeeService.getEmployeesPerPage(pageNumber, pageSize, sortField, sortDirection);
        List<EmployeeDto> filteredEmployeesPageContentDto = filteredEmployeesPage.getContent();
        List<EmployeeDto> allEmployeesPageContentDto = allEmployeesPage.getContent();
        List<Employee> filteredEmployeesPageContent = modelMapper.map(filteredEmployeesPageContentDto, new TypeToken<List<Employee>>() {}.getType());
        List<Employee> allEmployeesPageContent = modelMapper.map(allEmployeesPageContentDto, new TypeToken<List<Employee>>() {}.getType());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("switchedSortDirection", sortDirection.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("searchDto", new SearchDto());
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

    @PostMapping("/page")
    public String getSearchResult(Model model,
                                  @RequestParam int pageSize, @RequestParam int pageNumber,
                                  @RequestParam String sortField, @RequestParam String sortDirection,
                                  @RequestParam("searchedName") Optional<String> searchedName, @ModelAttribute SearchDto searchDto) {
        String searchedNameValue = searchedName.isPresent() ? searchDto.getName() : "";
        String sortParams = "";
        if (sortField == null || sortField.isBlank()) {
            sortField = "";
            sortDirection = "";
            sortParams = "&sortField=" + sortField + "&sortDirection=" +sortDirection;
        }
        findEmployeesPagination(model, pageSize, pageNumber, sortField, sortDirection, searchedNameValue);
        return "redirect:/employees/page?pageSize=" + pageSize + "&pageNumber=" + pageNumber + sortParams + "&searchedName=" + searchedNameValue;
    }
}
