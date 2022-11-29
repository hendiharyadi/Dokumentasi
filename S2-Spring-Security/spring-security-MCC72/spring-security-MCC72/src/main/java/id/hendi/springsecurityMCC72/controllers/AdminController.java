/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.springsecurityMCC72.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    
    @GetMapping
    public String home() {
        return "Welcome to ADMIN Home Page";
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "Welcome to ADMIN Dashboard";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "Welcome to ADMIN Landing Page";
    }
}
