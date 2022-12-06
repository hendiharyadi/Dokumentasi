package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Employee;
import id.kelompok8.SpringSecurityKelompok8.services.EmployeeService;
import id.kelompok8.SpringSecurityKelompok8.services.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

@AllArgsConstructor
@RestController
@RequestMapping("employee")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
public class EmployeeController {

    private EmployeeService employeeService;
    private UserEntityService userEntityService;
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Map<String, Object>> getAllMap() {
        return employeeService.getAllMap();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:blue;\">Welcome to ROLE Dashboard</p>";
    }
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:blue;\">Welcome to ROLE Landing Page</p>";
    }
    
    // @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Employee insert(@RequestBody UserRegistrationDto employee) {
        Employee e = employeeService.insert(employee);
        userEntityService.insert(employee);
        userEntityService.sendVerifyMail(employee);
        return e;
    }
    
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping
    public Employee update(@RequestBody UserRegistrationDto employee) {
        return employeeService.update(employee);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id) {
        return employeeService.deleteById(id);
    }
}
