/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @Column (length = 25)
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<Country> countries;

//    public Region(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
    public Region(int id){
        this.id = id;
    }
}
