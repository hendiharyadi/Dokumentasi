/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class User {
    
    @Id
    private int id;  

    @Column (length = 255)
    private String username;
    
    @Column (length = 255)
    private String password;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id") 
    private Employee employee;
    
    @OneToMany(mappedBy = "user")
    Set<User_Role> roles;
    
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Employee employee;
//    @JsonIgnore
//    @OneToOne(mappedBy = "region", fetch = FetchType.LAZY)
//    @Column(nullable = true)
//    private List<Country> countries;

//    public Region(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//    public Region(int id){
//        this.id = id;
//    }
}
