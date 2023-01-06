/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.controllers;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
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
        return "<p style=\"color:red;\">Welcome to ADMIN Dashboard</p>";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:red;\">Welcome to ADMIN Landing Page</p>";
    }
}
