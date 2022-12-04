/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
import id.kelompok8.SpringSecurityKelompok8.services.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("role")
@AllArgsConstructor
public class RoleController {
    
    private RoleService roleService;
    
    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }
    
    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id){
        return roleService.getById(id);
    }
    
    @PostMapping
    public Role insert(@RequestBody Role role){
        return roleService.create(role);
    }
    
    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role){
        return roleService.update(id, role);
    }
    
    @DeleteMapping("/{id}")
    public Role delete (@PathVariable Integer id){
        return roleService.delete(id);
    }
}
