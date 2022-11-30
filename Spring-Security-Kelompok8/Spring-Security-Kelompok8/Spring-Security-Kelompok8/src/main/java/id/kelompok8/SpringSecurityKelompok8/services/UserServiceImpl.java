/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.User;
import id.kelompok8.SpringSecurityKelompok8.models.dto.UserDto;
import id.kelompok8.SpringSecurityKelompok8.repositories.MyUserDetailsService;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class UserServiceImpl implements MyUserDetailsService{
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public User save(UserDto userdto) {
        User user = new User(userdto.getUsername(), userdto.getEmail(), passwordEncoder.encode(userdto.getPassword()));

        return userRepository.save(user);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found!!!")
                );

        UserDetails ud = new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities());
        return ud;
    }
    
    public List<GrantedAuthority> getAuthorities(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    
}
