/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.services.UserEntityService;
import java.util.List;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
 */
@AllArgsConstructor
@RestController
@RequestMapping("activatedUser")
public class ActivatedUserController {
private UserEntityService userEntityService;
    @GetMapping
    public Map<String, Object> home() {
        
        return userEntityService.getLoginResponse();
    }

    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:red;\">Welcome to ACTIVATED USER Dashboard</p>";
    }

    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:red;\">Welcome to ACTIVATED USER Landing Page</p>";
    }
}
