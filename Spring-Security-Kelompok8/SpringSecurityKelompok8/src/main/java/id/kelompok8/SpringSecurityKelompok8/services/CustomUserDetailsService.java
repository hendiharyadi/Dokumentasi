/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author DevidBa
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserEntityRepository ur;

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
                user.getUsername(), user.getPassword(), getAuthorities(user.getUserRole()));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles
                .forEach(
                        role -> {
//                            String roleName = "ROLE_" + role.getName().toUpperCase();
                            authorities.add(new SimpleGrantedAuthority(role.getName()));
                            role.getPrivileges().forEach(
                                    privilege -> {
//                                        String privilegeName = privilege.getName().toUpperCase();
                                        authorities.add(new SimpleGrantedAuthority(privilege.getName()));
                                    }
                            );
                        }
                );
        System.out.println(authorities);
        return authorities;
    }

}
