/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.User;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import id.kelompok8.SpringSecurityKelompok8.services.UserService;
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
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    
    private UserService userService;
    
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    
    @PostMapping
    public User insert(@RequestBody User user){
        return userService.create(user);
    }
    
    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user){
        return userService.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public User delete (@PathVariable Integer id){
        return userService.delete(id);
    }
}
