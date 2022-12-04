/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.kelompok8.SpringSecurityKelompok8.services;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import id.kelompok8.SpringSecurityKelompok8.repositories.UserEntityRepository;

/**
 *
 * @author robby
 */
@Service
@AllArgsConstructor
public class MailContentBuilder {
 
    private TemplateEngine templateEngine;
    private UserEntityRepository userRepository;
 
//    @Autowired
//    public MailContentBuilder(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }

    public String build(String username) {
        String verifyCode = userRepository.findByUsername(username).get().getVerificationCode(); //get dari database
        String verifyLink = "http://localhost:8081/security/user/verify/" + username +"/" +verifyCode;
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("verifyLink", verifyLink);
        return templateEngine.process("mail", context);
    }
 
}
