/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.Department;
import id.hendi.serversideMCC72.services.DepartmentService;
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
@RequestMapping("department")
public class DepartmentController {
    
    private DepartmentService departmentService;
    
    @Autowired
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }
    
    @GetMapping("test")
    public String test (){
        return "Hai";
    }
    
    @GetMapping
    public List<Department> getAll(){
        List<Department> countries = departmentService.findAll();
        return countries;
    }
    
    @GetMapping("save")
    public Department save(){
        Department department = new Department(1, "RnD", null, null, null);
        return departmentService.save(department);
    }
    
    @GetMapping("id")
    public Department getReferenceById(){
        Department department = departmentService.getReferenceById(1);
        return department;
    }
    
}
