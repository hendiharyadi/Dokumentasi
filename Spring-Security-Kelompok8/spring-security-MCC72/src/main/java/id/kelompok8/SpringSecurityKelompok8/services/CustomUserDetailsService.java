/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author DevidBa
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserEntityRepository ur;
    private User user;
    private UserEntity userEntity;

    @Autowired
    public CustomUserDetailsService(UserEntityRepository ur) {
        this.ur = ur;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = ur.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!!!")
                );

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    
    public UserEntity getUser(){
        return userEntity;
    }
}
