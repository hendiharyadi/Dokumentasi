/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author robby
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegistrationDto {

    private String username;

    private String password;

    private Boolean isActive;

    private String verificationCode;
    
    private Integer roleId; //role

}
