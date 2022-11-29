/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserDTO;
import id.kelompok8.SpringSecurityKelompok8.models.entity.User;
import id.kelompok8.SpringSecurityKelompok8.services.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("registration")
public class MainController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    UserDTO userDTO = new UserDTO();
    model.addAttribute("user", userDTO);
    return "register";
    }
    
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDTO.getEmail());
    
        userService.registerNewUserAccount(userDTO);
        return "redirect:/register?success";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
