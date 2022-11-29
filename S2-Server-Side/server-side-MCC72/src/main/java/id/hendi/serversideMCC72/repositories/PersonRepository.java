/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.repositories;

import id.hendi.serversideMCC72.models.entity.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hendi
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
    //JPQL
    @Query("SELECT p FROM Person p WHERE p.age = ?1")
    public List<Person> findByAgeJPQL(Integer age);
    
    @Query ("SELECT p FROM Person p WHERE p.fullName = :fullName")
    public Person findByFullNameJPQL(@Param("fullName") String fullName);
    
    //Native SQL
    @Query(
    value = "SELECT * FROM tb_person p WHERE p.person_age = :age", 
    nativeQuery = true
    )
    public List<Person> findByAgeNative(@Param("age") Integer age);
    
    @Query(
    value = "SELECT * FROM tb_person p WHERE p.person_full_name LIKE :fullName", 
    nativeQuery = true
    )
    public List<Person> findByFullNameNative(@Param("fullName") String fullName);
    
    //Query Method
    public Person findByEmail(String email);
    
    public List<Person> findByFullNameContainsOrderByIdDesc(String fullName);
    
    public List<Person> findByFullNameStartingWith(String fullName);
    
    public List<Person> findByFullNameContainsOrEmailContains(String fullName, String email);
    
    public Optional<Person> findByUsername(String username);
}
