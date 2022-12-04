/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import id.kelompok8.SpringSecurityKelompok8.repositories.UserRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author robby
 */
@Service
@AllArgsConstructor
public class MailContentBuilder {
 
    private TemplateEngine templateEngine;
    private UserRepository userRepository;
 
//    @Autowired
//    public MailContentBuilder(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }

    public String build(String username) {
        String verifyCode = userRepository.findByUsername(username).get().getVerificationCode(); //get dari database
        String verifyLink = "http://localhost:8081/security/registration/verify/" + username +"/" +verifyCode;
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("verifyLink", verifyLink);
        return templateEngine.process("mail", context);
    }
 
}
