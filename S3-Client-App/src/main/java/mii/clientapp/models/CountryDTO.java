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
public class CountryDTO {
    private String code;
    private String name;
    private Integer regionId;
   
}
