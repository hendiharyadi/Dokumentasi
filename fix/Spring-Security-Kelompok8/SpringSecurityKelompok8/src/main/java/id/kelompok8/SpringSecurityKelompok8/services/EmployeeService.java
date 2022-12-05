/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Employee;
import id.kelompok8.SpringSecurityKelompok8.repositories.EmployeeRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class EmployeeService {
    
    private EmployeeRepository er;
    
    public List<Employee> findAll(){
        if(er.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data available.");
        }
        
        for (Employee employee : er.findAll()){
            employee.getFirst_name();
            employee.getLast_name();
            employee.getEmail();
            employee.getPhone_number();
            employee.getHire_date();
            employee.getSalary();
            employee.getCommission_pct();
        }
        return er.findAll();
    }
    
    public List<Map<String, Object>> getAllMap(){
        return er.findAll().stream().map(employee -> {
            Map<String, Object> m = new HashMap<>();
            m.put("employeeId", employee.getId());
            m.put("employeeFirstName", employee.getFirst_name());
            m.put("employeeLastName", employee.getLast_name());
            m.put("employeeEmail", employee.getEmail());
            m.put("employeePhoneNumber", employee.getPhone_number());
            m.put("employeeHireDate", employee.getHire_date());
            m.put("employeeSalary", employee.getSalary());
            m.put("employeeCommissionPct", employee.getCommission_pct());
            return m;
        }).collect(Collectors.toList());
    }
    
    public Employee findById(Integer id){
        if (!er.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist.");
        }
        return er.findById(id).get();
    }
    
    public Employee insert(Employee e) {
                if (er.existsById(e.getId())) {
                    throw new ResponseStatusException(HttpStatus.FOUND, "Data is exist!!!");
                }
            
                return er.save(e);
            }
        

    public Employee update(Employee e) {
        if(!er.existsById(e.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        
        return er.save(e);
    }

    public String deleteById(Integer id) {
        if (!er.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        Employee e = findById(id);
        er.deleteById(id);
        return "Delete for " + e.getFirst_name() + "success!!!";
    }

}
