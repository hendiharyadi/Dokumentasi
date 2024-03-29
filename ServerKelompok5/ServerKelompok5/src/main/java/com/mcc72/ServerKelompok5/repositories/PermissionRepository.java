/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc72.ServerKelompok5.repositories;

import com.mcc72.ServerKelompok5.models.entity.Employee;
import com.mcc72.ServerKelompok5.models.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hendi
 */
@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Integer>{
    Optional<Permission> findPermissionByEmployee(Employee employee);

    List<Permission> findPermissionByManager(Employee manager);
    
    @Query("SELECT p FROM Permission as p WHERE p.employee.id= ?1 ORDER BY (CASE WHEN p.status = 'PENDING' THEN 0 WHEN p.status = 'ACCEPTED' THEN 1 ELSE 2 END)")
    List<Permission> orderPermission(Integer id);
}
