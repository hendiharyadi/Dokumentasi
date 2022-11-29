/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.Employee;
import id.hendi.serversideMCC72.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    
    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    
    @GetMapping("test")
    public String test (){
        return "Hai";
    }
    
    @GetMapping
    public List<Employee> getAll(){
        List<Employee> countries = employeeService.findAll();
        return countries;
    }
    
//    @GetMapping("save")
////    public Employee save(){
//////        Employee employee = new Employee(1, "Hendi", "Haryadi", "hendiharyadi@outlook.com", "085798819015", "2022-10-01", 80808080, 0, null, null, null, null, null, null);
//////        return employeeService.save(employee);
////    }
    
    @GetMapping("id")
    public Employee getReferenceById(){
        Employee employee = employeeService.getReferenceById(1);
        return employee;
    }
    
}
