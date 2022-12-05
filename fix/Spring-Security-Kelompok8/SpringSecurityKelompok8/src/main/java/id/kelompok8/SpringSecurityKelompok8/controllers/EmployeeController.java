package id.kelompok8.SpringSecurityKelompok8.controllers;

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
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("employee")
public class EmployeeController {

    private EmployeeService employeeService;
    
    @GetMapping
    public List<Map<String, Object>> getAllMap(){
        return employeeService.getAllMap();
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:blue;\">Welcome to ROLE Dashboard</p>";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:blue;\">Welcome to ROLE Landing Page</p>";
    }
    
    @PostMapping
    public Employee insert(@RequestBody Employee employee){
        return employeeService.insert(employee);
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee){
        return employeeService.update(employee);
    }
    
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        return employeeService.deleteById(id);
    }
}
