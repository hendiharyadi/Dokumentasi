/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.repositories;

import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
//import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DevidBa
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<UserEntity> findByUsername(String username);
    public User getByUsername (String username);
    
    @Query("UPDATE UserEntity u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String username);
}
