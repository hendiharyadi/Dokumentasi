/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.springsecurityMCC72.services;

import id.hendi.springsecurityMCC72.models.User;
import id.hendi.springsecurityMCC72.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
public class UserService {
    
    private UserRepository userRepository;
    
    public User create(User user){
        if (user.getId()!= null){
            throw new ResponseStatusException(
            HttpStatus.FOUND, "Data is already existed...");
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new ResponseStatusException(
            HttpStatus.FOUND, "Username is already existed..."
            );
        }
        return userRepository.save(user);
    }
}
