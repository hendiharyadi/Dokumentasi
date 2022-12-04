package id.kelompok8.SpringSecurityKelompok8.controllers;

import id.kelompok8.SpringSecurityKelompok8.models.dto.request.EmailRequest;
import id.kelompok8.SpringSecurityKelompok8.services.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class EmailController {

  private EmailService emailService;
  
  @GetMapping("/verify/{username}/{token}")
  public String verify(@PathVariable("username") String username,@PathVariable("token") String token){
       System.out.println("Token controller : "+token);
       Boolean isActivated = emailService.verify(username,token);
         return isActivated ? "redirect:/registration?activated" : "redirect:/registration?errVerify";
    }
}
