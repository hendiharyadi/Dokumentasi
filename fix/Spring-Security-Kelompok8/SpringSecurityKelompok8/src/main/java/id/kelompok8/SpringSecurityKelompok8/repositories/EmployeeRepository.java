/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.repositories;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hendi
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    public Optional <Employee> findByEmail(String email);
}
