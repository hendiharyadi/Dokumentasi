/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (length = 6)
    private int id;
    
    @Column(length = 20, nullable = true)
    private String first_name;
    
    @Column (length = 25)
    private String last_name;
    
    @Column (length = 25, unique = true)
    private String email;
    
    @Column (length = 20, nullable = true)
    private String phone_number;
    
    private String hire_date;
    
    @Column (length = 8, nullable = true)
    private int salary;
    
    @Column (nullable = true)
    private int commission_pct;
    
    @OneToMany(mappedBy = "employee")
    private List<History> histories;
    
    @OneToMany(mappedBy = "manager")
    private List<Employee> managers;
    
    @OneToMany(mappedBy = "manager")
    private List<Department> departments;
    
    @ManyToOne
    @JoinColumn (name = "manager")
    private Employee manager;
    
    @ManyToOne
    @JoinColumn (name = "department")
    private Department department;
    
    @ManyToOne
    @JoinColumn (name = "job")
    private Job job;
}
