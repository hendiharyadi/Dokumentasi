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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class EmailService {
    

    private JavaMailSender mailSender;
    
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
    
    //Template processing
//    public String build(String message) {
//        return templateEngine.process("mailTemplate", context);
//    }
    //Mime Mail Message
    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try{ 
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(emailRequest.getTo());
            helper.setText(emailRequest.getName());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());
            
            String html = "<!doctype html>\n" +
                "<html lang=\"en\"" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Email</title>\n" +
                "</head>\n" +
                    "<style>\n" +
                    "body {background-color: #FAF9F7;}\n" +
                    "h1   {color: #080204; padding-left:8%; padding-top:5%; font-size:300%; font-family: Arial, sans-serif;}\n" +
                    "p    {color: #19160f; padding-left:8%; padding-top:1%; font-size:102%;}\n" +
                    "button {border: none; color: white; margin-left: 8%; margin-top: 4%; padding: 2%; text-align: center; text-decoration: none; display: inline-block; font-size: 102%; cursor: pointer; background-color:#7e714d;}\n"+
                    "a {color: white; text-decoration:none;}"+
                    "</style>\n" +
                "<body>\n" +
                "<div><h1> Hey, " + emailRequest.getName() + "!</h1></div>\n" +
                "\n" +
                "<div><p>" + emailRequest.getBody() + "</p></div>\n" +
                "<div><p>Thank you and have a nice day!</p></div>" +
                "<button><a href=\"https://hndhryd-portfolio.netlify.app/\" target=\"_blank\">Click here for the portfolio.</a></button>" +
                "</body>\n" +
                "</html>\n";
            helper.setText(html, true);
            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttach()));
            
            helper.addAttachment(file.getFilename(), file);
            
            mailSender.send(message);
            System.out.println("Email with attachment has been sent!");
        } catch (Exception e){
            throw new IllegalStateException("Email has failed to be sent!");
        }
        return emailRequest;
    }
    
//    public EmailResponse (EmailRequest emailRequest){
//        EmailResponse response = new EmailResponse();
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            // set mediaType
//            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                    StandardCharsets.UTF_8.name());
//
//            Map<String, Object> model = new HashMap<>();
//            model.put("Name", request.getSubject());
//            model.put("location", "Jakarta,Indonesia");
//            // add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
//
//            Template t = config.getTemplate("mail.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//
//            helper.setTo(request.getTo());
//            helper.setText(html, true);
//            helper.setSubject(request.getSubject());
//
//            mailSender.send(message);
//
//
//            response.setMessage("mail send to : " + request.getTo());
//            response.setStatus(Boolean.TRUE);
//
//        } catch (MessagingException | IOException | TemplateException e) {
//            response.setMessage("Mail Sending failure : "+e.getMessage()); // SL4j and longer
//            response.setStatus(Boolean.FALSE);
//        }
//
//        return response;
//    }
}
