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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hendi
 */
@Repository
@Transactional
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<UserEntity> findByUsername(String username);
    
    @Modifying
    @Query("update UserEntity u set u.failedAttempt = ?1 where u.id = ?2")
    int setFailedAttemptForUser(Integer failedAttempt, Integer id);
}
