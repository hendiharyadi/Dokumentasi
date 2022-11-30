/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.UserDto;
import id.kelompok8.SpringSecurityKelompok8.repositories.MyUserDetailsService;
import id.kelompok8.SpringSecurityKelompok8.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hendi
 */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private MyUserDetailsService myUserDetailService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto userdto) {
        myUserDetailService.save(userdto);
        return "redirect:/registration?success";
    }
}
