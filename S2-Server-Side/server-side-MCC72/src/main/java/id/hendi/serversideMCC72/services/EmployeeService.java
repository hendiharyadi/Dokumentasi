/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Employee;
import id.hendi.serversideMCC72.repositories.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }
    
    public Employee save(Employee c){
        return employeeRepository.save(c);
    }
    
    public Employee getReferenceById(Integer id){
        return employeeRepository.getReferenceById(id);
    }
    
}
