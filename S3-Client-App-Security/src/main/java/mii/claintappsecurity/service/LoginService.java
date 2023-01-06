/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.claintappsecurity.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mii.claintappsecurity.model.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author MSI-JO
 */
@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/login")
    private String url;

    public Boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<Map<String,List<String>>> response
                    = restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            new HttpEntity(loginRequest),
                            new ParameterizedTypeReference<Map<String,List<String>>>() {
                            });
            if (response.getStatusCode() == HttpStatus.OK) {
                setPrinciple(response.getBody(), loginRequest);
                return true;
            }
        } catch (Exception e) {
//            throw new UsernameNotFoundException("Username or Password incorrect " + e);
        }
        return false;
    }
    
    public void setPrinciple(Map<String,List<String>> res, LoginRequest loginRequest){
        List<GrantedAuthority> authorities = res.get("authorities")
                .stream().map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .collect(Collectors.toList());
        
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), 
                        loginRequest.getPassword(), 
                        authorities);
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
