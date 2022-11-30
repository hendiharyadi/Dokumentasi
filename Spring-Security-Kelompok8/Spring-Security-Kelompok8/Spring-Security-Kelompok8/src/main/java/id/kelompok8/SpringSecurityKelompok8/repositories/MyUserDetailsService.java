/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.repositories;

import id.kelompok8.SpringSecurityKelompok8.models.User;
import id.kelompok8.SpringSecurityKelompok8.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Hendi
 */
public interface MyUserDetailsService extends UserDetailsService{
    User save(UserDto userdto);
}
