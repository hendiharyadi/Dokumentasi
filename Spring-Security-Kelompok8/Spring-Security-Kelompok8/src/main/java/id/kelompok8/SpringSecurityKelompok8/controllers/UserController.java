/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import id.kelompok8.SpringSecurityKelompok8.services.UserServiceImpl;
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
    
    private UserServiceImpl userService;
    
    @GetMapping
    public List<UserEntity> getAll(){
        return userService.getAll();
    }
    
    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Integer id){
        return userService.getById(id);
    }
    
    @PostMapping
    public UserEntity insert(@RequestBody UserEntity user){
        return userService.create(user);
    }
    
    @PutMapping("/{id}")
    public UserEntity update(@PathVariable Integer id, @RequestBody UserEntity user){
        return userService.update(id, user);
    }
    
    @DeleteMapping("/{id}")
    public UserEntity delete (@PathVariable Integer id){
        return userService.delete(id);
    }
}
