/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.entity.User;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
    
    private UserRepository ur;
    
    @Autowired
    public MyUserDetailsService (UserRepository ur){
        this.ur = ur;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ur.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!!!")
                );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getRole().toString()))
                            .collect(Collectors.toList()));
    }
    
//    public List<GrantedAuthority> getAuthorities(){
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//    }
}
