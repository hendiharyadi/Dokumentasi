/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.serversideMCC72.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //secondary UUID
    @Column(length = 2, unique = true)
    private String code;

    private String name;

    //FK
    @ManyToOne
    private Region region;
}
