/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.serversideMCC72.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author DevidBa
 */
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Region {
    
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

//    @Column(name = "region_name")
    private String name;
    
    @OneToMany(mappedBy = "region")
    private List<Country> countries;
}
