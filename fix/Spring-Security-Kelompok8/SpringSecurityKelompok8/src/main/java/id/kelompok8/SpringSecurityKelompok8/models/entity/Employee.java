/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "employee")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id", length = 6)
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
    
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserEntity user;
}
