/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc72.ServerKelompok5.controllers;


import com.mcc72.ServerKelompok5.models.entity.UserEntity;
import com.mcc72.ServerKelompok5.services.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hendi
 */
@RestController
@AllArgsConstructor
// @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class MainController {

    private UserEntityService ues;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserEntity userEntity) throws Exception {

        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(
                userEntity.getUsername(),
                userEntity.getPassword()
        );
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth); // Set Principle

        // Authoritiy, Username
        Map<String, Object> response = new HashMap<>();
        response.put("authorities", auth.getAuthorities()
                .stream().map(authority -> authority.getAuthority())
                .collect(Collectors.toList()));

        return response;
    }


    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("get-user")
    public String getUser() {
//        ALT 1
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        return SecurityContextHolder.getContext().getName();

//        ALT 2
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("get-roles")
    public String getRoles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().toString();
    }
}
