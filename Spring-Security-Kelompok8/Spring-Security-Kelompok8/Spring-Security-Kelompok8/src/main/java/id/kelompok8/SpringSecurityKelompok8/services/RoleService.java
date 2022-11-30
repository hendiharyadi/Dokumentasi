/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.Role;
import id.kelompok8.SpringSecurityKelompok8.repositories.RoleRepository;
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
public class RoleService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    public List<Role> getAll(){
        if (roleRepository.findAll().isEmpty()){
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Data not found!!!");
        }
        return roleRepository.findAll();
    }
    
    public Role getById(Integer id){
        return roleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!!!"));
    }
    
    public Role create(Role role){
        return roleRepository.save(role);
    }
    
    public Role update(Integer id, Role role){
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }
    
    public Role delete(Integer id){
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
