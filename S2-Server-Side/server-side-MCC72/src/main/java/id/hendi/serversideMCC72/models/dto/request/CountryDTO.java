/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.dto.request;

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
