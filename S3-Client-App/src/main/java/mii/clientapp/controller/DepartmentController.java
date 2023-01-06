/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller;

import lombok.AllArgsConstructor;
import mii.clientapp.models.Department;
import mii.clientapp.service.DepartmentService;
import mii.clientapp.service.EmployeeService;
import mii.clientapp.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author MSI-JO
 */
@Controller // HTML
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;
    private EmployeeService employeeService;
    private LocationService locationService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        return "department/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("managers", employeeService.getAll());
        model.addAttribute("locations", locationService.getAll());
        return "department/detail";
    }

    @GetMapping("/create")
    public String createView(Department department, Model model) {
        model.addAttribute("managers", employeeService.getAll());
        model.addAttribute("locations", locationService.getAll());
        return "department/create-form";
    }

    @PostMapping
    public String create(Department department) {
        if(department.getManager().getId() == 0){
        department.setManager(null);
        }
        if(department.getLocation().getId() == 0){
        department.setLocation(null);
        }
        departmentService.create(department);
        return "redirect:/department";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Department department, Model model) {
        model.addAttribute("locations", locationService.getAll());
        model.addAttribute("managers", employeeService.getAll());
        model.addAttribute("department", departmentService.getById(id));
        return "department/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Department department) {
        if(department.getManager().getId() == 0){
        department.setManager(null);
        }
        if(department.getLocation().getId() == 0){
        department.setLocation(null);
        }
        departmentService.update(id, department);
        return "redirect:/department";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        departmentService.deleteById(id);
        return "redirect:/department";
    }

}
