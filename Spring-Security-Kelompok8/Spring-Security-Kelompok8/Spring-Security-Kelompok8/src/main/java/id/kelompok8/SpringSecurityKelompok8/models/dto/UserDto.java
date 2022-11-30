/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models.dto;

import lombok.Data;

/**
 *
 * @author Hendi
 */
@Data
public class UserDto {
    
    private String username;
    private String email;
    private String password;

}
