/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.services;

import id.dev.springsecurityMCC72.models.dto.LoginRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author MSI-JO
 */
@Service
@AllArgsConstructor
public class LoginService {
    
    private AuthenticationManager authenticationManager;
    
    public Object login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
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
    
}