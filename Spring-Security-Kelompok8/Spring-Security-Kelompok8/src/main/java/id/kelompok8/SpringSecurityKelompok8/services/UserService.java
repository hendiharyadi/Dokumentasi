/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.models.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author robby
 */
public interface UserService extends UserDetailsService{
 UserEntity save(UserRegistrationDto registrationDto);
}