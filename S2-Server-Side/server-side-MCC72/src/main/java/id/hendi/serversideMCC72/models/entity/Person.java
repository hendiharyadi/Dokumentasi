/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "person_username", length = 20, nullable = false, unique = true)
    private String username;
    
    @Column(name = "person_full_name", length = 50, nullable = false)
    private String fullName;
    
    @Column(name = "person_email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "person_age", length = 3, nullable = false)
    private Integer age;
}
