/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.UserRegistrationDto;
import id.kelompok8.SpringSecurityKelompok8.services.EmailService;
import id.kelompok8.SpringSecurityKelompok8.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author robby
 */
@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
private EmailService emailService;
//    public UserRegistrationController(UserService userService) {
//        super();
//        this.userService = userService;
//    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        emailService.sendVerifyMail(registrationDto.getUsername());
        return "redirect:/registration?success";
    }
    
    @GetMapping("/verify/{username}/{token}")
  public String verify(@PathVariable String username,@PathVariable String token){
       Boolean isActivated = emailService.verify(username,token);
         return isActivated ? "redirect:/registration?activated" : "redirect:/registration?errVerify";
    }
    
}