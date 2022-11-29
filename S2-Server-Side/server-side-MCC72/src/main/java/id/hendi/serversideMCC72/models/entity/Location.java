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
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (length = 4)
    private int id;

    @Column(length = 40, nullable = true)
    private String street_address;

    @Column (length = 12, nullable = true)
    private String postal_code;
    
    @Column (length = 30)
    private String city;
    
    @Column (length = 25, nullable = true)
    private String state_province;
    
    @OneToMany(mappedBy = "location")
    private List<Department> departments;
    
    @ManyToOne
    @JoinColumn(name = "country")
    private Country country;
}

