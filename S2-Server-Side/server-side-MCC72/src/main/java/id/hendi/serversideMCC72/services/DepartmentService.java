/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Department;
import id.hendi.serversideMCC72.repositories.DepartmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class DepartmentService {
    
    private DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }
    
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }
    
    public Department save(Department c){
        return departmentRepository.save(c);
    }
    
    public Department getReferenceById(Integer id){
        return departmentRepository.getReferenceById(id);
    }
    
}
