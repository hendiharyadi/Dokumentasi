/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.repositories;

import id.kelompok8.SpringSecurityKelompok8.models.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Hendi
 */
public interface User_RoleRepository extends JpaRepository<User_Role, Integer>{
    
}
