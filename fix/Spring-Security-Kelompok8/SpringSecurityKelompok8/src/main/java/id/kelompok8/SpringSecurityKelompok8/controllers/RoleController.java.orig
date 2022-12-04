/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.services.RoleService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
 */
@AllArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {
 private RoleService roleService;

    //    localhost:8081/api/role
    @GetMapping
    public List<Map<String, Object>> getAllMap(){
        return roleService.getAllMap();
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:blue;\">Welcome to ROLE Dashboard</p>";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:blue;\">Welcome to ROLE Landing Page</p>";
    }
    
    //    localhost:8081/api/role
    @PostMapping
    public Role insert(@RequestBody Role role){
        return roleService.insert(role);
    }
    
//    localhost:8081/api/role
    @PutMapping
    public Role update(@RequestBody Role role){
        return roleService.update(role);
    }
    
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        return roleService.deleteById(id);
    }
}
