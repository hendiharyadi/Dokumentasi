/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.springsecurityMCC72.controllers;

import id.hendi.springsecurityMCC72.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
public class MainController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("get-user")
    public String getUser(){
        //ALT 1
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        return securityContext.getAuthentication().getName();
        //ALT 2
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    
    @GetMapping("get-roles")
    public String getRoles(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().toString();
    }
    
    @GetMapping("register")
    public String createUser(){
        String plainPassword = "123Mantap!";
        String username = "managerDB";
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(plainPassword));
        
        return user.getPassword();
    }
    
    @GetMapping
    public String home() {
        return "Welcome to Home Page";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "Welcome to Dashboard";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "Welcome to Landing Page";
    }
}
