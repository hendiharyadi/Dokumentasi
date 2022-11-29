/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.springsecurityMCC72.repositories;

import id.hendi.springsecurityMCC72.models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hendi
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    public Optional <User> findByUsername(String username);
}
