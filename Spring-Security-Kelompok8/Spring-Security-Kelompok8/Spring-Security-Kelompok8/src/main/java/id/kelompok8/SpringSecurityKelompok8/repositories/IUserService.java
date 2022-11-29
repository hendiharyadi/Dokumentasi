/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.repositories;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserDTO;
import id.kelompok8.SpringSecurityKelompok8.models.entity.User;

/**
 *
 * @author Hendi
 */
public interface IUserService {
    User registerNewUserAccount(UserDTO userDTO);
    User findUserByEmail(String email);
}
