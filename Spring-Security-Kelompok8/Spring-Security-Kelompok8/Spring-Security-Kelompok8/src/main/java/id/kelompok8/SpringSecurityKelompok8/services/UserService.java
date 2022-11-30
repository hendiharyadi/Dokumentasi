/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.User;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAll(){
        if (userRepository.findAll().isEmpty()){
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Data not found!!!");
        }
        return userRepository.findAll();
    }
    
    public User getById(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!!!"));
    }
    
    public User create(User user){
        return userRepository.save(user);
    }
    
    public User update(Integer id, User user){
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
    
    public User delete(Integer id){
        User user = getById(id);
        userRepository.delete(user);
        return user;
    }
    
}
