/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserDTO;
import id.kelompok8.SpringSecurityKelompok8.models.entity.Role;
import id.kelompok8.SpringSecurityKelompok8.models.entity.User;
import id.kelompok8.SpringSecurityKelompok8.repositories.IUserService;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class UserService implements IUserService{
    
    @Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUserAccount(UserDTO userDTO) {
        
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setEmail(userDTO.getEmail());
        
        Role role = roleRepository.findByName("ROLE_ADMIN");

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

        return userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
