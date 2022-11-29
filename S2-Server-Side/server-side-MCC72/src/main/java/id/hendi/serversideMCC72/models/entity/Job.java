/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   
    @Column(length = 10, unique = true)
    private String code;
    
    @Column(length = 35)
    private String title;
    
    @Column(length = 10, nullable = true)
    private int min_salary;
    
    @Column(length = 10, nullable = true)
    private int max_salary;
    
    @OneToMany(mappedBy = "job")
    private List<History> histories;
    
    @OneToMany(mappedBy = "job")
    private List<Employee> employees;
    
}
