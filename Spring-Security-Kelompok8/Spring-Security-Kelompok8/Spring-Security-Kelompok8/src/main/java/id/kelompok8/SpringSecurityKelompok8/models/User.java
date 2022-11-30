/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    @Column (length = 255)
    private String username;
    
    private String email;
    
    @Column (length = 255)
    private String password;
    
    private Boolean isActive;
    
    private String verificationCode;
    
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
//    private List<User_Role> roles;
    
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
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName="id"))
    private List<User_Role> roles;

    public User(String username, String email, String encode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
