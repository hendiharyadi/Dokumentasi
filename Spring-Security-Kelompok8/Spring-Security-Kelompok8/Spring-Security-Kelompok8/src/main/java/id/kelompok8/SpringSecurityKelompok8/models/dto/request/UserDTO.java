/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models.dto.request;

import lombok.Data;

/**
 *
 * @author Hendi
 */
@Data
public class UserDTO {
    
    private String username;
    
    private String password;
    
    private String email;
}
