/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.models.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
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

    private String first_name;

    private String last_name;

    private String email;

    private String phone_number;

    private String username;

    private String password;
    
    private List<Integer> roles;

}
