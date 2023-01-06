/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.clientapp.models.Employee;
import mii.clientapp.service.DepartmentService;
import mii.clientapp.service.EmployeeService;
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
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        return "employee/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/detail";
    }

    @GetMapping("/create")
    public String createView(Employee employee, Model model) {
        model.addAttribute("managers", employeeService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "employee/create-form";
    }

    @PostMapping
    public String create(Employee employee) {
        if(employee.getManager().getId() == 0){
        employee.setManager(null);
        }
        if(employee.getDepartment().getId() == 0){
        employee.setDepartment(null);
        }
        employeeService.create(employee);
        return "redirect:/employee";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Employee employee, Model model) {
        List<Employee> managerList = employeeService.getAll();

        managerList.remove(employeeService.getById(id));

          model.addAttribute("departments", departmentService.getAll());
           model.addAttribute("managers", managerList);
        model.addAttribute("employee", employeeService.getById(id));
        return "employee/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Employee employee) {
        if(employee.getManager().getId() == 0){
        employee.setManager(null);
        }
        if(employee.getDepartment().getId() == 0){
        employee.setDepartment(null);
        }
        employeeService.update(id, employee);
        return "redirect:/employee";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        employeeService.deleteById(id);
        return "redirect:/employee";
    }

}
