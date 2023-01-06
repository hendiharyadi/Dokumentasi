/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.models;

import lombok.Data;

/**
 *
 * @author Hendi
 */
@Data
public class LocationDTO {
    
    private String street_address;
    
    private String postal_code;
    
    private String city;
    
    private String state_province;
    
    private Integer countryId;
}
