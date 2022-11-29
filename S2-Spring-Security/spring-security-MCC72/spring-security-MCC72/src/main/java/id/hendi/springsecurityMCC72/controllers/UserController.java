/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.springsecurityMCC72.controllers;

import id.hendi.springsecurityMCC72.models.User;
import id.hendi.springsecurityMCC72.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    private UserService userService;
    
    @GetMapping
    public String home() {
        return "Welcome to USER Home Page";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "Welcome to USER Dashboard";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "Welcome to USER Landing Page";
    }
    
    @PostMapping
    public User insert(@RequestBody User user){
        return userService.create(user);
    }
}
