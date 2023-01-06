/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    
    private int id;

    private String street_address;

    private String postal_code;
    
    private String city;
    
    private String state_province;
     
    private Country country;
}

