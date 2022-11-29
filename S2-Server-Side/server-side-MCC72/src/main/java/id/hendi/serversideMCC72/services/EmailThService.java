/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.dto.request.EmailRequest;
import java.io.File;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class EmailThService {
    

    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    
    //Simple Mail Message
    public EmailRequest sendSimpleMessage(EmailRequest emailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
         
        mailSender.send(message);
        System.out.println("Email has been sent!");
        return emailRequest;
    }
    
     public String build(EmailRequest emailRequest) {
        Context context = new Context();
        context.setVariable("name", emailRequest.getName());
        context.setVariable("body", emailRequest.getBody());
        return templateEngine.process("mailTemplate", context);
    }

    //Mime Mail Message
    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try{ 
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(emailRequest.getTo());
            helper.setText("Hey" + emailRequest.getName());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttach()));
            
            helper.addAttachment(file.getFilename(), file);
            
            mailSender.send(message);
            System.out.println("Email with attachment has been sent!");
        } catch (Exception e){
            throw new IllegalStateException("Email has failed to be sent!");
        }
        return emailRequest;
    }
    
}
