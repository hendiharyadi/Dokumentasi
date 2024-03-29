/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import id.kelompok8.SpringSecurityKelompok8.services.UserEntityService;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DevidBa
 */
@AllArgsConstructor
@RestController
@RequestMapping("user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private UserEntityService userEntityService;
    
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping
    public List<Map<String, Object>> getAllMap(){
        return userEntityService.getAllMap();
    }
    
    @PostMapping
    public UserEntity insert(@RequestBody UserRegistrationDto userEntity){
        System.out.println("controller here");
       UserEntity urd = userEntityService.insert(userEntity);
        userEntityService.sendVerifyMail(userEntity);
        return urd;
    }

    @GetMapping("/verify/{username}/{token}")
    public String verify(@PathVariable String username,@PathVariable String token){
       Boolean isActivated = userEntityService.verify(username,token);
         return isActivated ? "Account Activated." : "Invalid Verification Code.";
    }
    
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:blue;\">Welcome to USER Dashboard</p>";
    }
    
    @PreAuthorize("hasAuthority('READ_USER')")
    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:blue;\">Welcome to USER Landing Page</p>";
    }
}
