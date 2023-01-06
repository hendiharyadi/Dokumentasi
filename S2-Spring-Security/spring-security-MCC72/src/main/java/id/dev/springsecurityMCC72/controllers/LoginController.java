/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.controllers;

import id.dev.springsecurityMCC72.models.dto.LoginRequest;
import id.dev.springsecurityMCC72.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MSI-JO
 */
@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
    
    private LoginService loginService;
    
    @PostMapping
    public Object login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
    
}
